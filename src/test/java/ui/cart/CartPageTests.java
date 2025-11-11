package ui.cart;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;

public class CartPageTests extends BaseTests {
    CartPage cartPage;

    @Test
    public void testVerifySubscriptionOnCartPage() {
        reportLogger = report.createTest("Test Case 11: Verify Subscription in Cart Page");

        reportLogger.log(Status.INFO, "Clicking cart button");
        cartPage = homePage.goToCart();
        String expectedFooterTitle = "Subscription";

        reportLogger.log(Status.INFO, "Asserting the footer's title");
        Assert.assertEquals(cartPage.getFooterTitle(),
                            expectedFooterTitle.toUpperCase(),
                            "The footer title should be: Subscription");
        reportLogger.log(Status.PASS, "The footer title is correct");

        reportLogger.log(Status.INFO, "Typing a subscription email");
        cartPage.typeSubscriptionEmail("test@testmail.com");

        reportLogger.log(Status.INFO, "Subscribing");
        cartPage.clickSubscribeButton();
        Assert.assertTrue(cartPage.isSubscriptionSuccessMessageVisible());
        reportLogger.log(Status.PASS, "Successfully subscribed");
    }

    @Test
    public void testVerifyProductQuantityInCart() {
        reportLogger = report.createTest("Test Case 13: Verify product quantity in cart");

        reportLogger.log(Status.INFO, "Clicking on 'View Product' button of the first product");
        ProductDetailPage productDetailPage = homePage.viewProduct(1);

        reportLogger.log(Status.INFO, "Asserting that the user is viewing the selected product's details");
        Assert.assertTrue(productDetailPage.isUserViewingProductDetails());
        reportLogger.log(Status.PASS, "The user is viewing the selected product's details");

        reportLogger.log(Status.INFO, "Changing the quantity to 4");
        productDetailPage.selectQuantity("4");

        reportLogger.log(Status.INFO, "Adding the products to the cart");
        productDetailPage.addProductsToCart();

        reportLogger.log(Status.INFO, "Clicking the 'View Cart' button");
        cartPage = productDetailPage.viewCart();

        reportLogger.log(Status.INFO, "Asserting that the quantity is 4");
        Assert.assertEquals(cartPage.getQuantity(),
                           "4",
                           "The quantity does not match with the expected amount");
        reportLogger.log(Status.PASS, "The quantity is correct");
    }

    @Test
    public void testRemoveProductsFromCart() {
        reportLogger = report.createTest("Test Case 17: Remove Products From Cart");

        reportLogger.log(Status.INFO, "Scrolling down");
        homePage.scrollDown(550);

        reportLogger.log(Status.INFO, "Adding products to the cart");
        homePage.addProductToCart(2);

        reportLogger.log(Status.INFO, "Selecting the 'Continue Shopping' option");
        homePage.continueShopping();

        reportLogger.log(Status.INFO, "Adding products to the cart");
        homePage.addProductToCart(3);

        reportLogger.log(Status.INFO, "Selecting the 'View Cart' option");
        cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Asserting that the user is on the Cart page");
        Assert.assertTrue(cartPage.isUserOnCartPage());
        reportLogger.log(Status.PASS, "The user is in the Cart page");

        reportLogger.log(Status.INFO, "Removing a product from the cart");
        cartPage.removeProductFromCart(1);

        reportLogger.log(Status.INFO, "Asserting that the product has been removed");
        Assert.assertEquals(cartPage.getProductsCount(),
                            1,
                            "The item was not deleted");
        reportLogger.log(Status.PASS, "The product has been removed");
    }
}
