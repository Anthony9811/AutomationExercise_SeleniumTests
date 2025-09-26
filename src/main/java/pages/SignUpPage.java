package pages;

import data.UserInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage extends BasePage{
    private By accountInformationTitle = By.xpath("//div[@class = 'login-form']//h2[1]");
    private By passwordField = By.cssSelector("input[data-qa='password']");
    private  By firstNameField = By.cssSelector("input[data-qa='first_name']");
    private By lastNameField = By.cssSelector("input[data-qa='last_name']");
    private By companyField = By.cssSelector("input[data-qa='company']");
    private By addressField = By.cssSelector("input[data-qa='address']");
    private By countriesDropdown = By.cssSelector("select[data-qa='country']");
    private By stateField = By.cssSelector("input[data-qa='state']");
    private By cityField = By.cssSelector("input[data-qa='city']");
    private By zipCodeField = By.cssSelector("input[data-qa='zipcode']");
    private By mobileNumberField = By.cssSelector("input[data-qa='mobile_number']");
    private  By createAccountButton = By.cssSelector("button[data-qa='create-account']");
    private By newsletterCheckBox = By.id("newsletter");
    private By specialOffersCheckBox = By.id("optin");


    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isAccountInformationHeaderVisible() {
        return isElementDisplayed(accountInformationTitle);
    }

    public void selectMrTitle() {
        By mrTitle = By.id("id_gender1");
        waitForElementToBeVisible(mrTitle);
        clickElement(mrTitle);
    }

    public void selectMrsTitle() {
        By mrsTitle = By.id("id_gender2");
        waitForElementToBeVisible(mrsTitle);
        clickElement(By.id("id_gender2"));
    }

    public void setPassword(String password) {
        typeText(passwordField, password);
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
        scrollElementIntoView(firstNameField);
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
        typeText(firstNameField, user.firstName);
        typeText(lastNameField, user.lastName);
        typeText(companyField, user.company);
        typeText(addressField, user.address);

        Select select = new Select(driver.findElement(countriesDropdown));
        select.selectByContainsVisibleText(user.country);

        typeText(stateField, user.state);
        typeText(cityField, user.city);
        typeText(zipCodeField, user.zipCode);
        typeText(mobileNumberField, user.mobileNumber);
    }

    public AccountCreatedPage clickOnCreateAccountButton() {
        scrollElementIntoView(createAccountButton);
        clickElement(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}
