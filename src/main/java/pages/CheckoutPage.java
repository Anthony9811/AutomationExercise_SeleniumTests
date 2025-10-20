package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By addressDetailsHeader = By.xpath("//h2[normalize-space()='Address Details']");
    private By reviewYourOrderHeader = By.xpath("//h2[normalize-space()='Review Your Order']");
    private By messageArea = By.cssSelector("textarea[name='message']");
    private By placeOrderButton = By.cssSelector("a[href='/payment'");
    private By deliveryName = By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");
    private By deliveryCompany = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][1]");
    private By deliveryAddress = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][2]");
    private By deliveryCountry = By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']");
    private By deliveryFullAddress = By.xpath("//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']");
    private By deliveryNumber = By.xpath("//ul[@id='address_delivery']//li[@class='address_phone']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isAddressDetailsHeaderVisible() {
        return isElementDisplayed(addressDetailsHeader);
    }

    public Boolean isReviewYourOrderHeaderVisible() {
        return isElementDisplayed(reviewYourOrderHeader);
    }

    public void writeAComment(String comment) {
        scrollElementIntoView(messageArea);
        waitForElementToBeVisible(messageArea);
        typeText(messageArea, comment);
    }

    public PaymentPage placeOrder() {
        clickElement(placeOrderButton);
        return new PaymentPage(driver);
    }

    public String getDeliveryName() {
        return getElementText(deliveryName);
    }

    public String getDeliveryCompany() {
        return getElementText(deliveryCompany);
    }

    public String getDeliveryAddress() {
        return getElementText(deliveryAddress);
    }

    public String getDeliveryCountry() {
        return getElementText(deliveryCountry);
    }

    public String getDeliveryFullAddress() {
        return getElementText(deliveryFullAddress);
    }

    public String getDeliveryPhoneNumber() {
        return getElementText(deliveryNumber);
    }
}
