package pages;

import data.UserInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {
    private WebDriver driver;
    private By accountInformationTitle = By.xpath("//div[@class = 'login-form']//h2[1]");
    private By passwordField = By.cssSelector("input[data-qa='password']");
    public  By firstNameField = By.cssSelector("input[data-qa='first_name']");
    private By lastNameField = By.cssSelector("input[data-qa='last_name']");
    private By companyField = By.cssSelector("input[data-qa='company']");
    private By addressField = By.cssSelector("input[data-qa='address']");
    private By countriesDropdown = By.cssSelector("select[data-qa='country']");
    private By stateField = By.cssSelector("input[data-qa='state']");
    private By cityField = By.cssSelector("input[data-qa='city']");
    private By zipCodeField = By.cssSelector("input[data-qa='zipcode']");
    private By mobileNumberField = By.cssSelector("input[data-qa='mobile_number']");
    public  By createAccountButton = By.cssSelector("button[data-qa='create-account']");
    private By newsletterCheckBox = By.id("newsletter");
    private By specialOffersCheckBox = By.id("optin");


    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isAccountInformationHeaderVisible() {
        return driver.findElement(accountInformationTitle).isDisplayed();
    }

    public void selectMrTitle() {
        driver.findElement(By.id("id_gender1")).click();
    }

    public void selectMrsTitle() {
        driver.findElement(By.id("id_gender2")).click();
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void scrollIntoView(By element){
        String script = "arguments[0].scrollIntoView(true);";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        while (!driver.findElement(element).isDisplayed()) {
            javascriptExecutor.executeScript(script);
        }
    }

    public void selectDateOfBirth(String dropdownName, String valueToSelect) {
        By dropdownLocator = switch (dropdownName.toLowerCase()) {
            case "day" -> By.cssSelector("select[data-qa='days']");
            case "month" -> By.cssSelector("select[data-qa='months']");
            case "year" -> By.cssSelector("select[data-qa='years']");
            default -> throw new IllegalArgumentException("Invalid date dropdown name: " + dropdownName);
        };

        WebElement selectedDropdown = driver.findElement(dropdownLocator);
        Select select = new Select(selectedDropdown);

        select.selectByContainsVisibleText(valueToSelect);
    }

    public void selectAllRegistrationCheckboxes() {
        WebElement newsletter = driver.findElement(newsletterCheckBox);
        WebElement specialOffers = driver.findElement(specialOffersCheckBox);

        if (!newsletter.isSelected()) {
            newsletter.click();
        }

        if (!specialOffers.isSelected()) {
            specialOffers.click();
        }
    }

    public void setUserInformation(UserInformation user) {
        driver.findElement(firstNameField).sendKeys(user.firstName);
        driver.findElement(lastNameField).sendKeys(user.lastName);
        driver.findElement(companyField).sendKeys(user.company);
        driver.findElement(addressField).sendKeys(user.address);

        Select select = new Select(driver.findElement(countriesDropdown));
        select.selectByContainsVisibleText(user.country);

        driver.findElement(stateField).sendKeys(user.state);
        driver.findElement(cityField).sendKeys(user.city);
        driver.findElement(zipCodeField).sendKeys(user.zipCode);
        driver.findElement(mobileNumberField).sendKeys(user.mobileNumber);
    }

    public AccountCreatedPage clickOnCreateAccountButton() {
        driver.findElement(createAccountButton).click();
        return new AccountCreatedPage(driver);
    }
}
