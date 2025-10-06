package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentDonePage extends BasePage {
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private By orderConfirmedMessage = By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p");
    private By contiueButton = By.cssSelector("a[data-qa='continue-button']");

    public PaymentDonePage(WebDriver driver) {
        super(driver);
    }

    public DeleteAccountPage clickOnDeleteAccount() {
        clickElement(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }

    public Boolean isConfirmationMessageVisible() {
        return isElementDisplayed(orderConfirmedMessage);
    }

    public void clickOnContinue() {
        clickElement(contiueButton);
    }
}
