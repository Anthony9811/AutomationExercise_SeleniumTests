package pages;

import components.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ProductsPage extends BasePage {
    private ProductActions productActions;

    private By searchInputField = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsTitle = By.xpath("//h2[normalize-space()='Searched Products']");
    private By foundProductName = By.cssSelector("div[class='single-products'] p");
    private By viewCartButton = By.cssSelector("p[class='text-center'] a");
    private By brandsContainer = By.className("brands-name");

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.productActions = new ProductActions(driver);
    }

    public void searchProduct(String productName) {
        typeText(searchInputField, productName);
        clickElement(searchButton);
    }

    public CartPage viewCart() {
        return productActions.viewCart(viewCartButton);
    }

    public Boolean isSearchedProductsHeaderVisible() {
        waitForElementToBeVisible(searchedProductsTitle);
        return isElementDisplayed(searchedProductsTitle);
    }

    public Boolean isBrandsContainerVisible() {
        scrollDown(500);
        return isElementDisplayed(brandsContainer);
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
        return productActions.viewProduct(productNumber);
    }

    /**
     * @param productNumber is 2-based due to the website's selectors order
     */
    public void addToCart(int productNumber) {
        productActions.hoverOverProductAndAddToCart(productNumber);
    }
}
