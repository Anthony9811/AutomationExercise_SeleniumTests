package pages;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class TestCasesPage {
    WebDriver driver;

    public TestCasesPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isUserOnTestCasesPage() {
        String currentUrl = driver.getCurrentUrl();
        return Objects.requireNonNull(currentUrl).contains("test_cases");
    }
}
