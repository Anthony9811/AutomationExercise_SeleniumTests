package pages;

import data.PaymentData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {
    private By nameOnCardField = By.cssSelector("input[data-qa='name-on-card']");
    private By cardNumberField = By.cssSelector("input[data-qa='card-number']");
    private By cvcField = By.cssSelector("input[data-qa='cvc']");
    private By expiryMonthField = By.cssSelector("input[data-qa='expiry-month']");
    private By expiryYearField = By.cssSelector("input[data-qa='expiry-year']");
    private By confirmOrderButton = By.cssSelector("button[data-qa='pay-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void setPaymentDetails(PaymentData cardData) {
        typeText(nameOnCardField, cardData.nameOnCard());
        typeText(cardNumberField, String.valueOf(cardData.cardNumber()));
        typeText(cvcField, String.valueOf(cardData.cvc()));
        typeText(expiryMonthField, cardData.expiryMonth());
        typeText(expiryYearField, cardData.expiryYear());
    }

    public PaymentDonePage confirmOrder() {
        WebElement confirmOrder = driver.findElement(confirmOrderButton);
        clickWithJS(confirmOrder);
        return new PaymentDonePage(driver);
    }
}
