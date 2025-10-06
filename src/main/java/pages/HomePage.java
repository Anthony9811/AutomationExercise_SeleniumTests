package pages;

import components.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private ProductActions productActions;

    private By carousel = By.id("slider-carousel");
    private By loggedInAsLocator = By.cssSelector("li:nth-child(10) a:nth-child(1)");
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private By viewCartButon_OnAddedProduct = By.xpath("//p[@class='text-center']//a");

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

    public DeleteAccountPage deleteAccount() {
        clickElement(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }
}
