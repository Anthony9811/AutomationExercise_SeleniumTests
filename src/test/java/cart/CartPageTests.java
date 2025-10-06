package cart;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;

public class CartPageTests extends BaseTests {
    CartPage cartPage;

    @Test
    public void testVerifySubscriptionOnCartPage() {
        cartPage = homePage.goToCart();
        String expectedFooterTitle = "Subscription";
        Assert.assertEquals(cartPage.getFooterTitle(),
                            expectedFooterTitle.toUpperCase(),
                            "The footer title should be: Subscription");

        cartPage.typeSubscriptionEmail("test@testmail.com");
        cartPage.clickSubscribeButton();
        Assert.assertTrue(cartPage.isSubscriptionSuccessMessageVisible());
    }

    @Test
    public void testVerifyProductQuantityInCart() {
        ProductDetailPage productDetailPage = homePage.viewProduct(1);
        Assert.assertTrue(productDetailPage.isUserViewingProductDetails());

        productDetailPage.selectQuantity("4");
        productDetailPage.addProductsToCart();
        cartPage = productDetailPage.viewCart();

        Assert.assertEquals(cartPage.getQuantity(),
                           "4",
                           "The quantity does not match with the expected amount");
    }

    @Test
    public void testRemoveProductsFromCart() {
        homePage.addProductToCart(2);
        homePage.continueShopping();
        homePage.addProductToCart(3);
        cartPage = homePage.viewCart_OnAddedProduct();
        Assert.assertTrue(cartPage.isUserOnCartPage());

        cartPage.removeProductFromCart(1);
        Assert.assertEquals(cartPage.getProductsCount(),
                            1,
                            "The item was not deleted");
    }
}
