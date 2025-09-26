package products;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class ProductsTests extends BaseTests {
    ProductsPage productsPage;

    @Test
    public void testVerifyAllProducts() {
        productsPage = homePage.clickOnProducts();
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        ProductDetailPage productDetailPage = productsPage.viewProduct(1);
        Assert.assertTrue(productDetailPage.isUserViewingProductDetails());

        Assert.assertTrue(productDetailPage.getProductName().contains("Blue Top"));
        Assert.assertTrue(productDetailPage.getCategory().contains("Women > Tops"));
        Assert.assertTrue(productDetailPage.getAvailability().contains("In Stock"));
        Assert.assertTrue(productDetailPage.getCondition().contains("New"));
        Assert.assertTrue(productDetailPage.getBrand().contains("Polo"));
    }

    @Test
    public void testSearchAProduct() {
        String productName = "fancy green top";
        String expectedProductName = "Fancy Green Top";

        productsPage = homePage.clickOnProducts();
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        productsPage.searchProduct(productName);
        Assert.assertTrue(productsPage.isSearchedProductsHeaderVisible());
        Assert.assertEquals(productsPage.getFoundProductName(),
                            expectedProductName,
                            "The product was not found");
    }
}
