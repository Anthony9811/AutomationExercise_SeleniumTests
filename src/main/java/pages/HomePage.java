package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By carousel = By.id("slider-carousel");
    private By loggedInAsLocator = By.cssSelector("li:nth-child(10) a:nth-child(1)");
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    private Boolean isLoggedInAsLocatorDisplayed() {
        try {
            return driver.findElement(loggedInAsLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    private void waitForLoggedInIndicator() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAsLocator));
    }

    public Boolean isTheCarouselDisplayed() {
        return driver.findElement(carousel).isDisplayed();
    }

    public Boolean isUserLoggedIn() {
        waitForLoggedInIndicator();
        return isLoggedInAsLocatorDisplayed();
    }

    public Boolean isUserLoggedOut() {
        waitForLoggedInIndicator();
        return isLoggedInAsLocatorDisplayed();
    }

    public DeleteAccountPage clickOnDeleteAccount() {
        driver.findElement(deleteAccountButton).click();
        return new DeleteAccountPage(driver);
    }
}
