package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.CartPage;
import pages.ProductDetailPage;

public class ProductActions extends BasePage {

    public ProductActions(WebDriver driver) {
        super(driver);
    }

    public CartPage viewCart(By viewCartButton) {
        waitForElementToBeVisible(viewCartButton);
        clickElement(viewCartButton);
        return new CartPage(driver);
    }

    public ProductDetailPage viewProduct(int productNumber) {
        By viewProductButtonLocator = By.cssSelector("a[href='/product_details/"+productNumber+"']");
        WebElement viewProductButton = driver.findElement(viewProductButtonLocator);

        scrollElementIntoView(viewProductButtonLocator);
        waitForElementToBeVisible(viewProductButtonLocator);
        clickWithJS(viewProductButton);
        return new ProductDetailPage(driver);
    }
}
