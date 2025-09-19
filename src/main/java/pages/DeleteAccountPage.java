package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPage {
    private WebDriver driver;
    private By accountDeletedTitle = By.cssSelector("h2[data-qa='account-deleted']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isAccountDeletedHeaderVisible() {
        return driver.findElement(accountDeletedTitle).isDisplayed();
    }

    public void clickOnContinue() {
        driver.findElement(continueButton).click();
    }
}
