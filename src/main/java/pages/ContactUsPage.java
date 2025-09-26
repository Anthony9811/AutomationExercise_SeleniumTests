package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Objects;

public class ContactUsPage extends BasePage{
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
        super(driver);
    }

    public Boolean isFormHeaderVisible() {
        return isElementDisplayed(contactFormHeader);
    }

    public void setName(String name) {
        typeText(nameField, name);
    }

    public void setEmail(String email) {
        typeText(emailField, email);
    }

    public void setSubject(String subject) {
        typeText(subjectField, subject);
    }

    public  void setMessage(String message) {
        typeText(messageField, message);
    }

    public void clickSubmitButton() {
        clickElement(submitButton);
    }

    public void alert_clickToAccept() {
        driver.switchTo().alert().accept();
    }

    public String getSubmitStatusMessage() {
        waitForElementToBeVisible(submitStatus);
        return getElementText(submitStatus);
    }

    public void goToHome() {
        clickElement(homeButton);
    }

    public Boolean isHomePageInView() {
        String homePageLink = "https://www.automationexercise.com/";
        waitForUrlToBe(homePageLink);
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
