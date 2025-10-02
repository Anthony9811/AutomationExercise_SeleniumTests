package homepage;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
