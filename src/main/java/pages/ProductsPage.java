package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isUserOnProductsPage() {
        String productsUrl = "https://www.automationexercise.com/products";
        return Objects.equals(driver.getCurrentUrl(), productsUrl);
    }

    public void scrollIntoView(WebElement element){
        String script = "arguments[0].scrollIntoView(true);";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        while (!element.isDisplayed()) {
            javascriptExecutor.executeScript(script);
        }
    }

    public ProductDetailPage viewProduct(int productNumber) {
        //mejorar la legibilidad de este código, hacer quizá una clase basepage para mover los scripts
        By viewProductButtonLocator = By.cssSelector("a[href='/product_details/"+productNumber+"']");
        WebElement viewProductButton = driver.findElement(viewProductButtonLocator);
        scrollIntoView(viewProductButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewProductButtonLocator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].click();";
        js.executeScript(script, viewProductButton);

        return new ProductDetailPage(driver);
    }
}
