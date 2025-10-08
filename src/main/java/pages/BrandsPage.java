package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class BrandsPage extends BasePage {
    By brandProductsTitle = By.xpath("//div[@class='features_items']//h2[@class='title text-center']");

    public BrandsPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isUserOnBrandPage() {
        String url = "https://www.automationexercise.com/brand_products";
        waitForUrlToContain(url);
        return Objects.requireNonNull(driver.getCurrentUrl()).contains(url);
    }

    public String getBrandProductsTitle() {
        return getElementText(brandProductsTitle);
    }
}
