package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentDonePage extends BasePage {
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");

    public PaymentDonePage(WebDriver driver) {
        super(driver);
    }

    public DeleteAccountPage clickOnDeleteAccount() {
        clickElement(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }
}
