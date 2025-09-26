package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeText(By element, String text) {
        driver.findElement(element).sendKeys(text);
    }

    public String getElementText(By element) {
        return driver.findElement(element).getText();
    }

    public void clickElement(By element) {
        driver.findElement(element).click();
    }

    public Boolean isElementDisplayed(By element) {
        return driver.findElement(element).isDisplayed();
    }

    public void waitForElementToBeVisible(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForUrlToBe(String link) {
        wait.until(ExpectedConditions.urlToBe(link));
    }

    public void scrollElementIntoView (By element) {
        String script = "arguments[0].scrollIntoView(true);";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        while (!isElementDisplayed(element)) {
            javascriptExecutor.executeScript(script);
        }
    }

    public void clickWithJS(WebElement element) {
        String script = "arguments[0].click();";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }
}
