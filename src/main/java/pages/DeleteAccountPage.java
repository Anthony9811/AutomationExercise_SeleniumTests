package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPage extends BasePage{
    private By accountDeletedTitle = By.cssSelector("h2[data-qa='account-deleted']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isAccountDeletedHeaderVisible() {
        return isElementDisplayed(accountDeletedTitle);
    }

    public void clickOnContinue() {
        clickElement(continueButton);
    }
}
