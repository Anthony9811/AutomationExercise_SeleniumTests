package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isUserOnProductsPage() {
        String productsUrl = "https://www.automationexercise.com/products";
        waitForUrlToBe(productsUrl);
        return Objects.equals(driver.getCurrentUrl(), productsUrl);
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
