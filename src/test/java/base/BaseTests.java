package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-features=PasswordManager");//disables password manager
        options.addArguments("--disable-popup-blocking");
        options.addExtensions(new File(System.getProperty("user.dir"), "resources/UBOLite.crx"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Prevents Chrome from offering to save passwords
        prefs.put("profile.password_manager_enabled", false); // Disables the built-in password manager
        prefs.put("profile.password_manager_leak_detection", false); // Disables password leak detection warnings
        prefs.put("autofill.profile_enabled", false); // Disables autofill for addresses and other forms
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        verifyHomePageVisibility();
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
}
