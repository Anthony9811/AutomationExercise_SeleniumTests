package pages;

import data.Brands;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    WebDriverWait wait;
    private By footer = By.cssSelector("div[class='single-widget'] h2");
    private By successfulSubscriptionMessage = By.xpath("//div[@class='alert-success alert']");
    private By subscriptionEmailAddress = By.id("susbscribe_email");
    private By subscribeButton = By.id("subscribe");
    private By continueShoppingButton = By.xpath("//button[normalize-space()='Continue Shopping']");

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

    public void waitForElementToBeInvisible(By element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitForUrlToBe(String link) {
        wait.until(ExpectedConditions.urlToBe(link));
    }

    public void scrollElementIntoView (By element) {
        String scriptToScrollElementIntoView = "arguments[0].scrollIntoView(true);";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        while (!isElementDisplayed(element)) {
            javascriptExecutor.executeScript(scriptToScrollElementIntoView);
        }
    }

    /**
     *
     * @param pixels specifies the amount of pixels to scroll down to
     */
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ")");
    }

    public void waitForUrlToContain(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }

    public void clickWithJS(WebElement element) {
        String scriptToClick = "arguments[0].click();";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(scriptToClick, element);
    }

    public void continueShopping() {
        waitForElementToBeVisible(continueShoppingButton);
        clickElement(continueShoppingButton);
    }

    public void scrollToTheBottom() {
        String scriptToScrollToTheBottom = "window.scrollTo(0, document.body.scrollHeight);";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(scriptToScrollToTheBottom);
    }

    public BrandsPage clickBrand(Brands brand) {
        String brandName = brand.getBrandName();
        String brandLocator = String.format("a[href='/brand_products/%s']", brandName);
        By brandElement = By.cssSelector(brandLocator);
        clickElement(brandElement);
        return new BrandsPage(driver);
    }

    /*
    The following methods are for testing the footer, since there's two
    test cases that ask for this I decided to take this approach instead
    of repeating the code
     */
    public void scrollToFooter() {
        scrollElementIntoView(footer);
        waitForElementToBeVisible(footer);
    }

    public String getFooterTitle() {
        return getElementText(footer);
    }

    public Boolean isSubscriptionSuccessMessageVisible() {
        return isElementDisplayed(successfulSubscriptionMessage);
    }

    public void typeSubscriptionEmail(String email) {
        typeText(subscriptionEmailAddress, email);
    }

    public void clickSubscribeButton() {
        clickElement(subscribeButton);
    }
}
