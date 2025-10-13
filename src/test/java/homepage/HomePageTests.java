package homepage;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class HomePageTests extends BaseTests {

    @Test
    public void testVerifySubscriptionOnHomePage() {
        String expectedFooterTitle = "Subscription";
        homePage.scrollToFooter();
        Assert.assertEquals(homePage.getFooterTitle(),
                            expectedFooterTitle.toUpperCase(),
                            "The footer title should be: Subscription");

        homePage.typeSubscriptionEmail("test@testmail.com");
        homePage.clickSubscribeButton();
        Assert.assertTrue(homePage.isSubscriptionSuccessMessageVisible());
    }

    @Test
    public void testAddToCartFromRecommended() {
        String expectedProductName = "Blue Top";
        homePage.scrollToTheBottom();
        Assert.assertTrue(homePage.isRecommendedItemsTitleVisible());

        homePage.addRecommendedProductToCart(1);
        CartPage cartPage = homePage.viewCart_OnAddedProduct();
        Assert.assertEquals(cartPage.getProductName(),
                            expectedProductName,
                            "The selected product name does not match");
    }
}
