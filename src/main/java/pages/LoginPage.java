package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By signUpText = By.xpath("//div[@class='signup-form']");
    private By nameField = By.name("name");
    private By emailField = By.cssSelector("input[data-qa='signup-email']");
    private By signUpButton = By.xpath("//button[normalize-space()='Signup']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public Boolean isSignUpTextVisible() {
        wait.until(ExpectedConditions.urlToBe("https://www.automationexercise.com/login"));
        return driver.findElement(signUpText).isDisplayed();
    }

    public  void setUsername(String username) {
        driver.findElement(nameField).sendKeys(username);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public SignUpPage clickSignUpButton() {
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }
}
