package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    /**
     * @param productNumber is 2-based due to the website's selectors order
     */
    public void hoverOverProductAndAddToCart(int productNumber) {
        String addButtonXPathExpression = "(//div[@class='col-sm-4'])["+ productNumber +"]//a[contains(text(),'Add to cart')]";

        By productLocator = By.xpath("(//div[@class='col-sm-4'])["+ productNumber +"]");
        By onHoverAddToCartButtonLocator = By.xpath(addButtonXPathExpression);
        WebElement product = driver.findElement(productLocator);

        Actions actions = new Actions(driver);

        scrollElementIntoView(productLocator);
        actions.moveToElement(product).perform();
        waitForElementToBeVisible(onHoverAddToCartButtonLocator);
        WebElement addToCartButton_OnHover = driver.findElement(onHoverAddToCartButtonLocator);
        clickWithJS(addToCartButton_OnHover);
    }
}
