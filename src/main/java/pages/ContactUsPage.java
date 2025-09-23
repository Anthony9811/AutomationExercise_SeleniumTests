package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Objects;

public class ContactUsPage {
    WebDriver driver;
    WebDriverWait wait;
    private By contactFormHeader = By.cssSelector("div[class='contact-form'] h2[class='title text-center']");
    private By nameField = By.cssSelector("input[data-qa='name']");
    private By emailField = By.cssSelector("input[data-qa='email']");
    private By subjectField = By.cssSelector("input[data-qa='subject']");
    private By messageField = By.cssSelector("textarea[data-qa='message']");
    private By fileUploadField = By.cssSelector("input[name='upload_file']");
    private By submitButton = By.cssSelector("input[data-qa='submit-button']");
    private By submitStatus = By.xpath("//div[@class='status alert alert-success']");
    private By homeButton = By.xpath("//a[@class='btn btn-success']");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void fillField(By elementLocator, String text) {
        driver.findElement(elementLocator).sendKeys(text);
    }

    public Boolean isFormHeaderVisible() {
        return driver.findElement(contactFormHeader).isDisplayed();
    }

    public void setName(String name) {
        fillField(nameField, name);
    }

    public void setEmail(String email) {
        fillField(emailField, email);
    }

    public void setSubject(String subject) {
        fillField(subjectField, subject);
    }

    public  void setMessage(String message) {
        fillField(messageField, message);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public void alert_clickToAccept() {
        driver.switchTo().alert().accept();
    }

    public String getSubmitStatusMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitStatus));
        return driver.findElement(submitStatus).getText();
    }

    public void goToHome() {
        driver.findElement(homeButton).click();
    }

    public Boolean isHomePageInView() {
        String homePageLink = "https://www.automationexercise.com/";
        wait.until(ExpectedConditions.urlToBe(homePageLink));
        return Objects.equals(driver.getCurrentUrl(), homePageLink);
    }

    public void uploadFile(String relativePathOfTheFile) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        String fullPath = currentWorkingDirectory + File.separator + relativePathOfTheFile;

        File file = new File(fullPath);
        driver.findElement(fileUploadField).sendKeys(file.getAbsolutePath());
        clickSubmitButton();
    }
}
