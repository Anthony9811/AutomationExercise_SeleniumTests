package products;

import base.BaseTests;
import data.Brands;
import data.ExpectedProduct;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrandsPage;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.ProductsPage;

import java.util.Arrays;
import java.util.List;

public class ProductsTests extends BaseTests {
    ProductsPage productsPage;

    @Test
    public void testVerifyAllProducts() {
        productsPage = homePage.goToProducts();
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

        productsPage = homePage.goToProducts();
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        productsPage.searchProduct(productName);
        Assert.assertTrue(productsPage.isSearchedProductsHeaderVisible());
        Assert.assertEquals(productsPage.getFoundProductName(),
                            expectedProductName,
                            "The product was not found");
    }

    @Test
    public void testAddProductsInCart() {
        CartPage cartPage;
        productsPage = homePage.goToProducts();
        productsPage.addToCart(2);
        productsPage.continueShopping();

        productsPage.addToCart(3);
        cartPage = productsPage.viewCart();

        List<ExpectedProduct> expectedItems = Arrays.asList(
                new ExpectedProduct("Rs. 500", 1, "Rs.500"),
                new ExpectedProduct("Rs. 400", 1, "Rs.400")
        );

        List<CartPage.ActualProduct> actualItems = cartPage.getCartProducts();

        Assert.assertEquals(actualItems.size(),
                expectedItems.size(),
                "The number of items on the cart does not match the amount selected");

        for (int i = 0; i < expectedItems.size(); i++) {
            ExpectedProduct expected = expectedItems.get(i);
            CartPage.ActualProduct actual = actualItems.get(i);

            Assert.assertEquals(actual.price(), expected.price, "Prices do not match");
            Assert.assertEquals(actual.quantity(), expected.quantity, "Quantity does not match");
            Assert.assertEquals(actual.totalPrice().replaceAll(" ", ""),
                                expected.totalPrice,
                                "Total price does not match");
        }
    }

    @Test
    public void testViewAndCartBrandProducts() {
        BrandsPage brandsPage;
        String expectedPageTitle = "BRAND -  " + Brands.Polo +" PRODUCTS";
        productsPage = homePage.goToProducts();
        Assert.assertTrue(productsPage.isBrandsContainerVisible());

        brandsPage = productsPage.clickBrand(Brands.Polo);
        Assert.assertTrue(brandsPage.isUserOnBrandPage());
        Assert.assertEquals(brandsPage.getBrandProductsTitle(),
                            expectedPageTitle,
                            "The titles don't match");

        brandsPage.clickBrand(Brands.HM);
        Assert.assertTrue(brandsPage.isUserOnBrandPage());
    }
}
