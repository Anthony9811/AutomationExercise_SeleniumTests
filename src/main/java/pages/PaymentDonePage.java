package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.Duration;

public class PaymentDonePage extends BasePage {
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private By orderConfirmedMessage = By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");
    private By downloadInvoiceButton = By.cssSelector("a[href='/download_invoice/500']");

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
        clickElement(continueButton);
    }

    public void downloadInvoice() {
        waitForElementToBeVisible(downloadInvoiceButton);
        clickElement(downloadInvoiceButton);
    }

    private void waitForFileDownload(String downloadPath, String fileName, int timeoutInSeconds) {
        Wait<File> wait = new FluentWait<>(new File(downloadPath))
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(file -> {
            File[] files = file.listFiles();
            if (file != null) {
                for (File f : files) {
                    if (f.getName().equals(fileName)) {
                        return true;
                    }
                }
            }
            return false;
        });
    }

    public Boolean isInvoiceDownloaded() {
        String fileName = "invoice.txt";
        String downloadPath = System.getProperty("user.dir") + File.separator + "Downloads";
        File downloadedFile = new File(downloadPath, fileName);
        waitForFileDownload(downloadPath, fileName, 20);
        return downloadedFile.exists();
    }
}
