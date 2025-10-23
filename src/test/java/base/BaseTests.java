package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.ExtentManager;
import utils.ScreenshotHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;
    protected ExtentReports report;
    protected ExtentTest reportLogger;


    @BeforeClass
    public void setUp() {
        String downloadPath = System.getProperty("user.dir") + File.separator + "Downloads";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-features=PasswordManager");//disables password manager
        options.addArguments("--disable-popup-blocking");
        options.addExtensions(new File(System.getProperty("user.dir"), "resources/UBOLite.crx"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Prevents Chrome from offering to save passwords
        prefs.put("profile.password_manager_enabled", false); // Disables the built-in password manager
        prefs.put("profile.password_manager_leak_detection", false); // Disables password leak detection warnings
        prefs.put("autofill.profile_enabled", false); // Disables autofill for addresses and other forms
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        initializeReport();
        verifyHomePageVisibility();
    }

    @BeforeSuite
    public void initializeReport() {
        report = ExtentManager.getInstance();
        try {
            cleanOldScreenshots();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    public void verifyHomePageVisibility() {
        String siteUrl = "https://www.automationexercise.com/";
        String pageTitle = "Automation Exercise";
        driver.get(siteUrl);
        homePage = new HomePage(driver);

        Assert.assertEquals(driver.getCurrentUrl(), siteUrl, "URL is incorrect");
        Assert.assertEquals(driver.getTitle(), pageTitle, "The title is incorrect");

        Assert.assertTrue(homePage.isTheCarouselDisplayed(), "The carousel is not displayed");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void recordTestResult(ITestResult testResult) {
        switch (testResult.getStatus()) {
            case ITestResult.FAILURE:
                reportLogger.log(Status.FAIL, "Test Failed: " + testResult.getThrowable());
                attachScreenshot(testResult.getName());
                break;

            case ITestResult.SUCCESS:
                reportLogger.log(Status.PASS, "Test Passed");
                attachScreenshot(testResult.getName());
                break;

            case ITestResult.SKIP:
                reportLogger.log(Status.SKIP, "Test Skipped: " + testResult.getThrowable());
                break;
        }
    }

    @AfterSuite
    public void flushReport() {
        report.flush();
    }

    private void attachScreenshot(String testName) {
        String screenshotPath = ScreenshotHelper.takeAScreenshot(driver, testName);
        Path reportDirectory = Paths.get(System.getProperty("user.dir"), "test-output");
        Path screenshotFile = Paths.get(screenshotPath);

        String relativePath = reportDirectory.relativize(screenshotFile).toString();
        reportLogger.addScreenCaptureFromPath(relativePath);
    }

    private void cleanOldScreenshots() throws IOException {
        Path screenshotsRoot = Paths.get(System.getProperty("user.dir"), "test-output", "screenshots");

        if (Files.exists(screenshotsRoot)) {
            // Walks through every file and folder under the screenshots directory
            try (Stream<Path> allPaths = Files.walk(screenshotsRoot)) {
                allPaths
                        .filter(path -> path.equals(screenshotsRoot)) // Ignores the root folder
                        .sorted(Comparator.comparingInt(Path::getNameCount).reversed()) // Sort so that the deepest files/folders are deleted first
                        .forEach(currentPath -> { // Deletes each file or folder
                            try {
                                Files.deleteIfExists(currentPath);
                            } catch (IOException e) {
                                System.err.println("Could not delete: " + currentPath + " -> " + e.getMessage());
                            }
                        });
            }
        }
    }
}
