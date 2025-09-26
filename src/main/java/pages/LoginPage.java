package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
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
        super(driver);
        waitForUrlToBe("https://www.automationexercise.com/login");
    }

    public Boolean isSignUpTextVisible() {
        return isElementDisplayed(signUpText);
    }

    public Boolean isLoginTextVisible() {
        return isElementDisplayed(loginText);
    }

    public  void setUsername(String username) {
        typeText(nameField, username);
    }

    public void setSignupEmail(String email) {
        typeText(signupEmailField, email);
    }

    public void setLoginEmail(String email) {
        typeText(loginEmailField, email);
    }

    public void setLoginPassword(String password) {
        typeText(passwordField, password);
    }

    public SignUpPage signUp() {
        clickElement(signUpButton);
        return new SignUpPage(driver);
    }

    public void login() {
        clickElement(loginButton);
        new HomePage(driver);
    }

    public String getLoginErrorMessage() {
        waitForElementToBeVisible(loginErrorMessage);
        return getElementText(loginErrorMessage);
    }

    public String getSignUpErrorMessage() {
        waitForElementToBeVisible(signupErrorMessage);
        return getElementText(signupErrorMessage);
    }


}
