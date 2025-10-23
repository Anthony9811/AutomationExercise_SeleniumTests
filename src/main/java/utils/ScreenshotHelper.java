package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    private static final String SCREENSHOTS_DIRECTORY = System.getProperty("user.dir") + "/test-output/screenshots/";

    public static String takeAScreenshot(WebDriver driver, String testName) {
        Path testFolder = Path.of(SCREENSHOTS_DIRECTORY, testName);

        try {
            Files.createDirectories(testFolder);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create screenshot folder: " + testFolder, e);
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        Path destination = testFolder.resolve(fileName);

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), destination);

            return destination.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + destination, e);
        }
    }
}
