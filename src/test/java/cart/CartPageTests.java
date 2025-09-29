package cart;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartPageTests extends BaseTests {

    @Test
    public void testVerifySubscriptionOnCartPage() {
        CartPage cartPage = homePage.clickOnCart();
        String expectedFooterTitle = "Subscription";
        Assert.assertEquals(cartPage.getFooterTitle(),
                            expectedFooterTitle.toUpperCase(),
                            "The footer title should be: Subscription");

        cartPage.typeSubscriptionEmail("test@testmail.com");
        cartPage.clickSubscribeButton();
        Assert.assertTrue(cartPage.isSubscriptionSuccessMessageVisible());
    }
}
