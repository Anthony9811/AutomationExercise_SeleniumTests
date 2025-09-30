package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public record ActualProduct(String price, int quantity, String totalPrice) {}

    public List<ActualProduct> getCartProducts() {
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        List<ActualProduct> actualProducts = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) {
            WebElement productRow = driver.findElement(By.xpath("//tr[@id='product-"+ i +"']"));

            WebElement priceElement = productRow.findElement(By.xpath("(//td[@class='cart_price'])["+i+"]"));
            String actualPrice = priceElement.getText();

            WebElement quantityElement = productRow.findElement(By.cssSelector("tr[id='product-"+i+"'] button[class='disabled']"));
            int actualQuantity = Integer.parseInt(quantityElement.getText());

            WebElement totalPriceElement = productRow.findElement(By.xpath("(//td[@class='cart_total'])["+i+"]"));
            String actualTotalPrice = totalPriceElement.getText();

            actualProducts.add(new ActualProduct(actualPrice, actualQuantity, actualTotalPrice));
        }
        return actualProducts;
    }
}
