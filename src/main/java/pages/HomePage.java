package pages;

import components.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private ProductActions productActions;

    private By carousel = By.id("slider-carousel");
    private By loggedInAsLocator = By.cssSelector("li:nth-child(10) a:nth-child(1)");
    private By viewCartButon_OnAddedProduct = By.xpath("//p[@class='text-center']//a");
    private By categoriesContainer = By.id("accordian");
    private By womenCategoryLocator = By.xpath("(//h4[@class='panel-title'])[1]");
    private By women_DressLocator = By.cssSelector("a[href='/category_products/1']");
    private By women_TopsLocator = By.cssSelector("a[href='/category_products/2']");
    private By women_SareeLocator = By.cssSelector("a[href='/category_products/7']");
    private By recommendedItemsTitle = By.cssSelector("div[class='recommended_items'] h2[class='title text-center']");
    private By scrollUpButton = By.id("scrollUp");
    private By siteSubtitle  = By.xpath("//div[@class='item active']//h2");

    public HomePage(WebDriver driver) {
        super(driver);
        this.productActions = new ProductActions(driver);
    }

    private void clickButton(String buttonName) {
        driver.findElement(By.cssSelector("a[href='/" + buttonName.toLowerCase() + "']")).click();
    }

    public LoginPage clickOnLoginButton() {
        clickButton("login");
        return new LoginPage(driver);
    }

    public void logout() {
        clickButton("logout");
    }

    /**
     * @param productNumber is 2-based due to the website's selectors order
     */
    public void addProductToCart(int productNumber) {
        productActions.hoverOverProductAndAddToCart(productNumber);
    }

    /**
     * @param productId takes values from 1 to 6 only
     */
    public void addRecommendedProductToCart(int productId) {
        String buttonXPathLocator = "//div[@class='carousel-inner']//a[@data-product-id='"+ productId +"']";
        By addToCartButton_OnRecommended = By.xpath(buttonXPathLocator);
        waitForElementToBeVisible(addToCartButton_OnRecommended);
        clickElement(addToCartButton_OnRecommended);
    }

    public ContactUsPage goToContactUs() {
        clickButton("contact_us");
        return new ContactUsPage(driver);
    }

    public TestCasesPage goToTestCases() {
        clickButton("test_cases");
        return new TestCasesPage(driver);
    }

    public ProductsPage goToProducts() {
        clickButton("products");
        return new ProductsPage(driver);
    }

    public ProductDetailPage viewProduct(int productNumber) {
        return productActions.viewProduct(productNumber);
    }

    public CartPage goToCart() {
        clickButton("view_cart");
        return new CartPage(driver);
    }

    public CartPage viewCart_OnAddedProduct() {
        waitForElementToBeVisible(viewCartButon_OnAddedProduct);
        clickElement(viewCartButon_OnAddedProduct);
        return new CartPage(driver);
    }

    private Boolean isLoggedInAsLocatorDisplayed() {
        try {
            return isElementDisplayed(loggedInAsLocator);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Boolean isTheCarouselDisplayed() {
        return isElementDisplayed(carousel);
    }

    public Boolean isUserLoggedIn() {
        waitForElementToBeVisible(loggedInAsLocator);
        return isLoggedInAsLocatorDisplayed();
    }

    public Boolean isUserLoggedOut() {
        return isLoggedInAsLocatorDisplayed();
    }

    public Boolean isRecommendedItemsTitleVisible() {
        return isElementDisplayed(recommendedItemsTitle);
    }

    public Boolean areCategoriesVisible() {
        return isElementDisplayed(categoriesContainer);
    }

    public void scrollUpUsingButton() {
        clickElement(scrollUpButton);
    }

    public Boolean isTheSiteSubtitleVisible() {
        waitForElementToBeVisible(siteSubtitle);
        return isElementDisplayed(siteSubtitle);
    }

    public CategoryProductsPage selectWomenSubcategory(String subcategory) {
        WebElement category = driver.findElement(womenCategoryLocator);
        WebElement dress = driver.findElement(women_DressLocator);
        WebElement tops = driver.findElement(women_TopsLocator);
        WebElement saree = driver.findElement(women_SareeLocator);

        clickWithJS(category);
        switch (subcategory.toUpperCase()) {
            case "DRESS":
                clickWithJS(dress);
                break;

            case "TOPS":
                clickWithJS(tops);
                break;

            case "SAREE":
                clickWithJS(saree);
                break;

            default:
                throw new IllegalArgumentException("Invalid subcategory name provided: " + subcategory);
        }

        return new CategoryProductsPage(driver);
    }
}
