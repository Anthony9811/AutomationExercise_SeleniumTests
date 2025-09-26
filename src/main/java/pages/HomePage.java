package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    private By carousel = By.id("slider-carousel");
    private By loggedInAsLocator = By.cssSelector("li:nth-child(10) a:nth-child(1)");
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private void clickButton(String buttonName) {
        driver.findElement(By.cssSelector("a[href='/" + buttonName.toLowerCase() + "']")).click();
    }

    public LoginPage clickOnLoginButton() {
        clickButton("login");
        return new LoginPage(driver);
    }

    public void clickOnLogoutButton() {
        clickButton("logout");
    }

    public ContactUsPage clickOnContactUs() {
        clickButton("contact_us");
        return new ContactUsPage(driver);
    }

    public TestCasesPage clickOnTestCases() {
        clickButton("test_cases");
        return new TestCasesPage(driver);
    }

    public ProductsPage clickOnProducts() {
        clickButton("products");
        return new ProductsPage(driver);
    }

    private Boolean isLoggedInAsLocatorDisplayed() {
        try {
            return isElementDisplayed(loggedInAsLocator);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Boolean isTheCarouselDisplayed() {
        return isElementDisplayed(carousel);
    }

    public Boolean isUserLoggedIn() {
        waitForElementToBeVisible(loggedInAsLocator);
        return isLoggedInAsLocatorDisplayed();
    }

    public Boolean isUserLoggedOut() {
        return isLoggedInAsLocatorDisplayed();
    }

    public DeleteAccountPage clickOnDeleteAccount() {
        clickElement(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }
}
