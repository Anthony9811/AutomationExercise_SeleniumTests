package products;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class ProductsTests extends BaseTests {

    @Test
    public void verifyAllProducts() {
        ProductsPage productsPage = homePage.clickOnProducts();
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        ProductDetailPage productDetailPage = productsPage.viewProduct(1);
        Assert.assertTrue(productDetailPage.isUserViewingProductDetails());

        Assert.assertTrue(productDetailPage.getProductName().contains("Blue Top"));
        Assert.assertTrue(productDetailPage.getCategory().contains("Women > Tops"));
        Assert.assertTrue(productDetailPage.getAvailability().contains("In Stock"));
        Assert.assertTrue(productDetailPage.getCondition().contains("New"));
        Assert.assertTrue(productDetailPage.getBrand().contains("Polo"));
    }
}
