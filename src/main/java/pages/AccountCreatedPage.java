package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage{
    private By accountCreatedTitle = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isAccountCreatedHeaderVisible() {
        return isElementDisplayed(accountCreatedTitle);
    }

    public HomePage clickContinueButton() {
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
