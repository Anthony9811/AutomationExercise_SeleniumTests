package pages;

import components.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class ProductDetailPage extends BasePage{
    private ProductActions productActions;

    private By productName = By.cssSelector("div[class='product-information'] h2");
    private By category = By.xpath("//div[@class='product-information']/p[1]");
    private By availability = By.xpath("//div[@class='product-information']/p[2]");
    private By condition = By.xpath("//div[@class='product-information']/p[3]");
    private By brand = By.xpath("//div[@class='product-information']/p[4]");
    private By quantity = By.id("quantity");
    private By addToCartButton = By.xpath("//button[normalize-space()='Add to cart']");
    private By viewCartButton = By.cssSelector("a[href='/view_cart'");
    private By writeYourReviewTitle = By.cssSelector("a[href='#reviews']");
    private By reviewName = By.id("name");
    private By reviewEmail = By.id("email");
    private By reviewComment = By.id("review");
    private By submitButton = By.id("button-review");
    private By successMessage = By.cssSelector("div[class='alert-success alert'] span");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.productActions = new ProductActions(driver);
    }

    public Boolean isUserViewingProductDetails() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("product_details");
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

    public void selectQuantity(String productQuantity) {
        WebElement quantitySelector = driver.findElement(quantity);
        Actions actions = new Actions(driver);
        actions.moveToElement(quantitySelector).click()
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL) //To select all text
                .build().perform();
        typeText(quantity, productQuantity);
    }

    public void addProductsToCart() {
        clickElement(addToCartButton);
    }

    public CartPage viewCart() {
        return productActions.viewCart(viewCartButton);
    }

    public Boolean isWriteYourReviewTitleVisible() {
        return isElementDisplayed(writeYourReviewTitle);
    }

    public void writeProductReview(String name, String email, String reviewComment) {
        typeText(reviewName, name);
        typeText(reviewEmail, email);
        typeText(this.reviewComment, reviewComment);
    }

    public void submitReview() {
        clickElement(submitButton);
    }

    public Boolean isSuccessMessageVisible() {
        waitForElementToBeVisible(successMessage);
        return isElementDisplayed(successMessage);
    }
}
