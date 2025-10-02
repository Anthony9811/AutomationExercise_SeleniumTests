package pages;

import components.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class ProductsPage extends BasePage {
    private ProductActions productActions;

    private By searchInputField = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsTitle = By.xpath("//h2[normalize-space()='Searched Products']");
    private By foundProductName = By.cssSelector("div[class='single-products'] p");
    private By continueShoppingButton = By.xpath("//button[normalize-space()='Continue Shopping']");
    private By viewCartButton = By.cssSelector("a[href='/view_cart'");

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.productActions = new ProductActions(driver);
    }

    public void searchProduct(String productName) {
        typeText(searchInputField, productName);
        clickElement(searchButton);
    }

    public void continueShopping() {
        waitForElementToBeVisible(continueShoppingButton);
        clickElement(continueShoppingButton);
    }

    public CartPage viewCart() {
        return productActions.viewCart(viewCartButton);
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
        return productActions.viewProduct(productNumber);
    }

    /**
     * @param productNumber is 2-based due to the website's selectors order
     */
    public void hoverOverProduct(int productNumber) {
        String addButtonXPathExpression = "(//div[@class='col-sm-4'])["+ productNumber +"]//a[contains(text(),'Add to cart')]";

        By productLocator = By.xpath("(//div[@class='col-sm-4'])["+ productNumber +"]");
        By onHoverAddToCartButtonLocator = By.xpath(addButtonXPathExpression);
        WebElement product = driver.findElement(productLocator);
        WebElement addToCartButton_OnHover = driver.findElement(onHoverAddToCartButtonLocator);

        Actions actions = new Actions(driver);

        scrollElementIntoView(productLocator);
        actions.moveToElement(product).perform();
        waitForElementToBeVisible(onHoverAddToCartButtonLocator);
        clickWithJS(addToCartButton_OnHover);
    }
}
