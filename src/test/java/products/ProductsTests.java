package products;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import data.Brands;
import data.ExpectedProduct;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Arrays;
import java.util.List;

public class ProductsTests extends BaseTests {
    ProductsPage productsPage;
    CartPage cartPage;
    ProductDetailPage productDetailPage;

    @Test
    public void testVerifyAllProducts() {
        reportLogger = report.createTest("Test Case 8: Verify All Products and product detail page");

        reportLogger.log(Status.INFO, "Going to 'Products' page");
        productsPage = homePage.goToProducts();

        reportLogger.log(Status.INFO, "Verifying that the user is on 'Products' page");
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        reportLogger.log(Status.INFO, "Viewing the first product's details");
        productDetailPage = productsPage.viewProduct(1);

        reportLogger.log(Status.INFO, "Verifying that the user is visualizing the selected product's details");
        Assert.assertTrue(productDetailPage.isUserViewingProductDetails());

        reportLogger.log(Status.INFO, "Verifying that the product's name matches the one selected");
        Assert.assertTrue(productDetailPage.getProductName().contains("Blue Top"));
        reportLogger.log(Status.PASS, "The names match");

        reportLogger.log(Status.INFO, "Verifying that the product's category matches the one selected");
        Assert.assertTrue(productDetailPage.getCategory().contains("Women > Tops"));
        reportLogger.log(Status.PASS, "The category matches");

        reportLogger.log(Status.INFO, "Verifying that the product is available");
        Assert.assertTrue(productDetailPage.getAvailability().contains("In Stock"));
        reportLogger.log(Status.PASS, "The product is available");


        reportLogger.log(Status.INFO, "Verifying that the product is new");
        Assert.assertTrue(productDetailPage.getCondition().contains("New"));
        reportLogger.log(Status.PASS, "The product is new");

        reportLogger.log(Status.INFO, "Verifying that the product's brand matches the one selected");
        Assert.assertTrue(productDetailPage.getBrand().contains("Polo"));
        reportLogger.log(Status.PASS, "The brand matches");
    }

    @Test
    public void testSearchAProduct() {
        String productName = "fancy green top";
        String expectedProductName = "Fancy Green Top";

        reportLogger = report.createTest("Test Case 9: Search Product");

        reportLogger.log(Status.INFO, "Going to 'Products' page");
        productsPage = homePage.goToProducts();

        reportLogger.log(Status.INFO, "Verifying that the user is on 'Products' page");
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        reportLogger.log(Status.INFO, "Typing and searching for: " + productName);
        productsPage.searchProduct(productName);

        reportLogger.log(Status.INFO, "Verifying that 'SEARCHED PRODUCTS' title is visible");
        Assert.assertTrue(productsPage.isSearchedProductsHeaderVisible());
        reportLogger.log(Status.PASS, "'SEARCHED PRODUCTS' title is visible");

        reportLogger.log(Status.INFO, "Verifying that the name of the product matches the one searched");
        Assert.assertEquals(productsPage.getFoundProductName(),
                            expectedProductName,
                            "The product was not found");
        reportLogger.log(Status.INFO, "The product name matches");
    }

    @Test
    public void testAddProductsInCart() {
        reportLogger = report.createTest("Test Case 12: Add Products in Cart");

        reportLogger.log(Status.INFO, "Going to 'Products' page");
        productsPage = homePage.goToProducts();

        reportLogger.log(Status.INFO, "Scrolling down to see the products");
        productsPage.scrollDown(500);

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        productsPage.addToCart(2);

        reportLogger.log(Status.INFO, "Clicking the 'Continue Shopping' button");
        productsPage.continueShopping();

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        productsPage.addToCart(3);

        reportLogger.log(Status.INFO, "Clicking the 'View Cart' button");
        cartPage = productsPage.viewCart();


        List<ExpectedProduct> expectedItems = Arrays.asList(
                new ExpectedProduct("Rs. 500", 1, "Rs.500"),
                new ExpectedProduct("Rs. 400", 1, "Rs.400")
        );

        List<CartPage.ActualProduct> actualItems = cartPage.getCartProducts();

        reportLogger.log(Status.INFO, "Verifying that both products were added to the cart");
        Assert.assertEquals(actualItems.size(),
                expectedItems.size(),
                "The number of items on the cart does not match the amount selected");

        for (int i = 0; i < expectedItems.size(); i++) {
            ExpectedProduct expected = expectedItems.get(i);
            CartPage.ActualProduct actual = actualItems.get(i);

            reportLogger.log(Status.INFO, "Verifying product " + (i+1) + " price");
            Assert.assertEquals(actual.price(), expected.price, "Prices do not match");

            reportLogger.log(Status.INFO, "Verifying product " + (i+1) + " quantity");
            Assert.assertEquals(actual.quantity(), expected.quantity, "Quantity does not match");

            reportLogger.log(Status.INFO, "Verifying product " + (i+1) + " total price");
            Assert.assertEquals(actual.totalPrice().replaceAll(" ", ""),
                                expected.totalPrice,
                                "Total price does not match");
        }
    }

    @Test
    public void testViewAndCartBrandProducts() {
        BrandsPage brandsPage;
        String expectedPageTitle = "BRAND - " + Brands.MAST_HARBOUR.getBrandName() +" PRODUCTS";

        reportLogger = report.createTest("Test Case 19: View & Cart Brand Products");

        reportLogger.log(Status.INFO, "Going to 'Products' page");
        productsPage = homePage.goToProducts();

        reportLogger.log(Status.INFO, "Verifying that 'Brands' are visible on left side bar");
        Assert.assertTrue(productsPage.isBrandsContainerVisible());

        reportLogger.log(Status.INFO, "Clicking on a brand");
        brandsPage = productsPage.clickBrand(Brands.MAST_HARBOUR);

        reportLogger.log(Status.INFO, "Verifying that the user is navigated to the selected brand page");
        Assert.assertTrue(brandsPage.isUserOnBrandPage());

        reportLogger.log(Status.INFO, "Verifying that brand products are displayed");
        Assert.assertEquals(brandsPage.getBrandProductsTitle().toUpperCase(),
                            expectedPageTitle.toUpperCase(),
                            "The titles don't match");

        reportLogger.log(Status.INFO, "Clicking on a different brand");
        brandsPage.clickBrand(Brands.HM);

        reportLogger.log(Status.INFO, "Verifying that the user is navigated to the selected brand page");
        Assert.assertTrue(brandsPage.isUserOnBrandPage());
    }

    @Test
    public void testSearchProductsAndVerifyCartAfterLogin() {
        String productToSearch = "fancy green top";
        String expectedProductName = "Fancy Green Top";

        reportLogger = report.createTest("Test Case 20: Search Products and Verify Cart After Login");

        reportLogger.log(Status.INFO, "Going to 'Products' page");
        productsPage = homePage.goToProducts();

        reportLogger.log(Status.INFO, "Verifying that the user is navigated to ALL PRODUCTS page successfully");
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        reportLogger.log(Status.INFO, "Typing and searching for " + productToSearch);
        productsPage.searchProduct(productToSearch);

        reportLogger.log(Status.INFO, "Verifying that the product name matches the search");
        Assert.assertEquals(productsPage.getFoundProductName(),
                expectedProductName,
                "The product was not found");
        reportLogger.log(Status.PASS, "The product name matches");

        reportLogger.log(Status.INFO, "Scrolling down to the product");
        productsPage.scrollDown(500);

        reportLogger.log(Status.INFO, "Adding the product to the cart");
        productsPage.addToCart(2);

        reportLogger.log(Status.INFO, "Clicking on the 'View Cart' button");
        cartPage = productsPage.viewCart();

        reportLogger.log(Status.INFO, "Verifying that the searched product is visible");
        Assert.assertTrue(cartPage.isProductVisible(expectedProductName));

        reportLogger.log(Status.INFO, "Clicking on the 'Login' button");
        LoginPage loginPage = cartPage.goToLogin();

        reportLogger.log(Status.INFO, "Typing the login email");
        loginPage.setLoginEmail("doe@testmail.com");

        reportLogger.log(Status.INFO, "Typing the login password");
        loginPage.setLoginPassword("password");

        reportLogger.log(Status.INFO, "Clicking the 'Login' button");
        loginPage.login();

        reportLogger.log(Status.INFO, "Going to the Cart page");
        cartPage = homePage.goToCart();

        reportLogger.log(Status.INFO, "Verifying that the added product is visible");
        Assert.assertTrue(cartPage.isProductVisible(expectedProductName));
    }

    @Test
    public void testAddReviewOnProduct() {
        reportLogger = report.createTest("Test Case 21: Add review on product");

        reportLogger.log(Status.INFO, "Going to 'Products' page");
        productsPage = homePage.goToProducts();

        reportLogger.log(Status.INFO, "Verifying that the user is navigated to ALL PRODUCTS page successfully");
        Assert.assertTrue(productsPage.isUserOnProductsPage());

        reportLogger.log(Status.INFO, "Viewing the first product's details");
        productDetailPage = productsPage.viewProduct(1);

        reportLogger.log(Status.INFO, "Scrolling down on the page");
        productDetailPage.scrollDown(400);

        reportLogger.log(Status.INFO, "Verifying that the 'Write Your Review' title is visible");
        Assert.assertTrue(productDetailPage.isWriteYourReviewTitleVisible());
        reportLogger.log(Status.PASS, "'Write Your Review' title is visible");

        reportLogger.log(Status.INFO, "Writing personal information and adding a review");
        productDetailPage.writeProductReview("Mark",
                                             "test@testmail.com",
                                             "Review Example");

        reportLogger.log(Status.INFO, "Submitting the review");
        productDetailPage.submitReview();

        reportLogger.log(Status.INFO, "Verifying success message 'Thank you for your review.' is visible");
        Assert.assertTrue(productDetailPage.isSuccessMessageVisible());
    }
}
