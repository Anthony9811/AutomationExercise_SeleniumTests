package homepage;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class HomePageTests extends BaseTests {

    @Test
    public void testVerifySubscriptionOnHomePage() {
        reportLogger = report.createTest("Test Case 10: Verify Subscription in home page");

        String expectedFooterTitle = "Subscription";

        reportLogger.log(Status.INFO, "Scrolling to the bottom of the page");
        homePage.scrollToFooter();

        reportLogger.log(Status.INFO, "Verifying that the footer title is 'Subscription'");
        Assert.assertEquals(homePage.getFooterTitle(),
                            expectedFooterTitle.toUpperCase(),
                            "The footer title should be: Subscription");
        reportLogger.log(Status.PASS, "The title is correct");

        reportLogger.log(Status.INFO, "Typing a subscription email");
        homePage.typeSubscriptionEmail("test@testmail.com");

        reportLogger.log(Status.INFO, "Clicking the 'Subscribe' button");
        homePage.clickSubscribeButton();

        reportLogger.log(Status.INFO, " Verifying success message 'You have been successfully subscribed!' is visible");
        Assert.assertTrue(homePage.isSubscriptionSuccessMessageVisible());
        reportLogger.log(Status.PASS, " Success message is visible");
    }

    @Test
    public void testAddToCartFromRecommended() {
        reportLogger = report.createTest("Test Case 22: Add To Cart From Recommended Items");
        String expectedProductName = "Blue Top";

        reportLogger.log(Status.INFO, "Scrolling to the bottom of the page");
        homePage.scrollToTheBottom();

        reportLogger.log(Status.INFO, "Verifying that 'RECOMMENDED ITEMS' are visible");
        Assert.assertTrue(homePage.isRecommendedItemsTitleVisible());
        reportLogger.log(Status.PASS, "'RECOMMENDED ITEMS' are visible");

        reportLogger.log(Status.INFO, "Adding a recommended product to the cart");
        homePage.addRecommendedProductToCart(1);

        reportLogger.log(Status.INFO, "Clicking the 'View Cart' button");
        CartPage cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Verifying that the selected product is displayed in cart page");
        Assert.assertEquals(cartPage.getProductName(),
                            expectedProductName,
                            "The selected product name does not match");
        reportLogger.log(Status.PASS, "'The selected product is displayed");
    }

    @Test
    public void testScrollUpUsingArrowButton() {
        reportLogger = report.createTest("Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality");

        reportLogger.log(Status.INFO, "Scrolling to the bottom of the page");
        homePage.scrollToTheBottom();

        reportLogger.log(Status.INFO, "Verifying 'SUBSCRIPTION' title is visible");
        Assert.assertEquals(homePage.getFooterTitle().toUpperCase(),
                            "SUBSCRIPTION",
                            "The title is not visible");
        reportLogger.log(Status.PASS, "'SUBSCRIPTION' title is visible");

        reportLogger.log(Status.INFO, "Clicking on arrow at bottom right side to move upward");
        homePage.scrollUpUsingButton();

        reportLogger.log(Status.INFO, "Verifying that 'Full-Fledged practice website for Automation Engineers' text is visible on screen");
        Assert.assertTrue(homePage.isTheSiteSubtitleVisible());
        reportLogger.log(Status.PASS, "The page has been scrolled up and the text is visible");
    }

    @Test
    public void testScrollUpWithoutUsingArrow() {
        reportLogger = report.createTest("Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality");

        reportLogger.log(Status.INFO, "Scrolling to the bottom of the page");
        homePage.scrollToTheBottom();

        reportLogger.log(Status.INFO, "Verifying 'SUBSCRIPTION' title is visible");
        Assert.assertEquals(homePage.getFooterTitle().toUpperCase(),
                "SUBSCRIPTION",
                "The title is not visible");
        reportLogger.log(Status.PASS, "'SUBSCRIPTION' title is visible");

        reportLogger.log(Status.INFO, "Scrolling to the top of the page");
        homePage.scrollToTheTop();

        reportLogger.log(Status.INFO, "Verifying that 'Full-Fledged practice website for Automation Engineers' text is visible on screen");
        Assert.assertTrue(homePage.isTheSiteSubtitleVisible());
        reportLogger.log(Status.PASS, "The page has been scrolled up and the text is visible");
    }
}
