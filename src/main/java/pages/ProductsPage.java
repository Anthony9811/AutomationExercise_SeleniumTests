package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ProductsPage extends BasePage {
    private By searchInputField = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsTitle = By.xpath("//h2[normalize-space()='Searched Products']");
    private By foundProductName = By.cssSelector("div[class='single-products'] p");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        typeText(searchInputField, productName);
        clickElement(searchButton);
    }

    public Boolean isSearchedProductsHeaderVisible() {
        waitForElementToBeVisible(searchedProductsTitle);
        return isElementDisplayed(searchedProductsTitle);
    }

    public String getFoundProductName() {
        return getElementText(foundProductName);
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
