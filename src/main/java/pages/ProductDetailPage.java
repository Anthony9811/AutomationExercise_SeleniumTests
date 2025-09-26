package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ProductDetailPage {
    WebDriver driver;
    private By productName = By.cssSelector("div[class='product-information'] h2");
    private By category = By.xpath("//div[@class='product-information']/p[1]");
    private By availability = By.xpath("//div[@class='product-information']/p[2]");
    private By condition = By.xpath("//div[@class='product-information']/p[3]");
    private By brand = By.xpath("//div[@class='product-information']/p[4]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isUserViewingProductDetails() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("product_details");
    }

    private String getElementText(By element) {
        return driver.findElement(element).getText();
    }

    public String getProductName() {
        return getElementText(productName);
    }

    public String getCategory() {
        return getElementText(category);
    }

    public String getAvailability() {
        return getElementText(availability);
    }

    public String getCondition() {
        return getElementText(condition);
    }

    public String getBrand() {
        return getElementText(brand);
    }
}
