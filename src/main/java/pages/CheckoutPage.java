package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By addressDetailsHeader = By.xpath("//h2[normalize-space()='Address Details']");
    private By reviewYourOrderHeader = By.xpath("//h2[normalize-space()='Review Your Order']");
    private By messageArea = By.cssSelector("textarea[name='message']");
    private By placeOrderButton = By.cssSelector("a[href='/payment'");

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
}
