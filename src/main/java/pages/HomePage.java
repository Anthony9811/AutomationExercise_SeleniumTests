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

    public LoginPage clickLoginButton() {
        clickButton("login");
        return new LoginPage(driver);
    }

    public Boolean isTheCarouselDisplayed() {
        return driver.findElement(carousel).isDisplayed();
    }

    public Boolean isUserLoggedIn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAsLocator));
        return driver.findElement(loggedInAsLocator).isDisplayed();
    }

    public DeleteAccountPage  clickOnDeleteAccount() {
        driver.findElement(deleteAccountButton).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(deleteAccountButton));
        return new DeleteAccountPage(driver);
    }
}
