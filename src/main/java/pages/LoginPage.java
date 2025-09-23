package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By signUpText = By.xpath("//h2[normalize-space()='New User Signup!']");
    private By loginText = By.xpath("//h2[normalize-space()='Login to your account']");
    private By nameField = By.name("name");
    private By signupEmailField = By.cssSelector("input[data-qa='signup-email']");
    private By loginEmailField = By.cssSelector("input[data-qa='login-email']");
    private By signUpButton = By.xpath("//button[normalize-space()='Signup']");
    private By passwordField = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By loginErrorMessage = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p");
    private By signupErrorMessage = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.automationexercise.com/login"));
    }

    private void waitForElementToBeVisible(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public Boolean isSignUpTextVisible() {
        return driver.findElement(signUpText).isDisplayed();
    }

    public Boolean isLoginTextVisible() {
        return driver.findElement(loginText).isDisplayed();
    }

    public  void setUsername(String username) {
        driver.findElement(nameField).sendKeys(username);
    }

    public void setSignupEmail(String email) {
        driver.findElement(signupEmailField).sendKeys(email);
    }

    public void setLoginEmail(String email) {
        driver.findElement(loginEmailField).sendKeys(email);
    }

    public void setLoginPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public SignUpPage signUp() {
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }

    public void login() {
        driver.findElement(loginButton).click();
        new HomePage(driver);
    }

    public String getLoginErrorMessage() {
        waitForElementToBeVisible(loginErrorMessage);
        return driver.findElement(loginErrorMessage).getText();
    }

    public String getSignUpErrorMessage() {
        waitForElementToBeVisible(signupErrorMessage);
        return driver.findElement(signupErrorMessage).getText();
    }


}
