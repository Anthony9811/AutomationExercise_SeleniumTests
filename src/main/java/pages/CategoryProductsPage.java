package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class CategoryProductsPage extends BasePage {
    WebDriverWait wait;
    private By pageTitle = By.xpath("//div[@class='features_items']//h2[@class='title text-center']");
    private By menCategoryLocator = By.xpath("(//h4[@class='panel-title'])[2]");
    private By men_TShirtsLocator = By.cssSelector("a[href='/category_products/3']");
    private By men_JeansLocator = By.cssSelector("a[href='/category_products/6']");

    public CategoryProductsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    public String getCurrentCategoryUrl() {
        return driver.getCurrentUrl();
    }

    public Boolean isUseOnCategoryProductsPage() {
        String url = "https://www.automationexercise.com/category_products";
        //wait.until(ExpectedConditions.urlContains(url));
        waitForUrlToContain(url);
        return Objects.requireNonNull(driver.getCurrentUrl()).contains(url);
    }

    /**
     *
     * @param subcategory only takes TSHIRTS (no hyphen) or JEANS as values
     */
    public void selectMenSubcategory(String subcategory) {
        WebElement menCategory = driver.findElement(menCategoryLocator);
        WebElement men_TShirts = driver.findElement(men_TShirtsLocator);
        WebElement men_Jeans = driver.findElement(men_JeansLocator);

        clickWithJS(menCategory);

        switch (subcategory.toUpperCase()) {
            case "TSHIRTS":
                clickWithJS(men_TShirts);
                break;

            case "JEANS":
            clickWithJS(men_Jeans);
            break;
        }
    }
}
