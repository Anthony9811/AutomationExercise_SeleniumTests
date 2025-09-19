package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage {
    private WebDriver driver;
    private By accountCreatedTitle = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isAccountCreatedHeaderVisible() {
        return driver.findElement(accountCreatedTitle).isDisplayed();
    }

    public HomePage clickContinueButton() {
        driver.findElement(continueButton).click();
        return new HomePage(driver);
    }
}
