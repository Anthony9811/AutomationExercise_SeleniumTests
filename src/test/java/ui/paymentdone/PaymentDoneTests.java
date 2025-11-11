package ui.paymentdone;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import data.PaymentData;
import data.PaymentDataProvider;
import data.UserDataProvider;
import data.UserInformation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class PaymentDoneTests extends BaseTests {

    @Test
    public void testDownloadInvoiceAfterPurchase() {
        CartPage cartPage;
        LoginPage loginPage;
        SignUpPage signUpPage;
        UserInformation userData;
        AccountCreatedPage accountCreatedPage;
        CheckoutPage checkoutPage;
        PaymentPage paymentPage;
        PaymentDonePage paymentDonePage;
        PaymentData cardData = PaymentDataProvider.getTestCardInformation();
        String cartUrl = "https://www.automationexercise.com/view_cart";

        reportLogger = report.createTest("Test Case 16: Place Order: Login before Checkout");

        reportLogger.log(Status.INFO, "Scrolling down to the products");
        homePage.scrollDown(500);

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        homePage.addProductToCart(2);

        reportLogger.log(Status.INFO, "Clicking on 'View Cart' button");
        cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Verifying that the user is in the Cart page");
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        reportLogger.log(Status.INFO, "Proceeding to checkout");
        cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Going to login page");
        loginPage = cartPage.goToLogin_OnCheckout();

        reportLogger.log(Status.INFO, "Typing signup username");
        loginPage.setUsername("testname");

        reportLogger.log(Status.INFO, "Typing signup email");
        loginPage.setSignupEmail("tau@testmail.com");

        reportLogger.log(Status.INFO, "Clicking on 'Signup' button");
        signUpPage = loginPage.signUp();

        reportLogger.log(Status.INFO, "Selecting 'Mr.' title");
        signUpPage.selectMrTitle();

        reportLogger.log(Status.INFO, "Typing password");
        signUpPage.setPassword("password");

        reportLogger.log(Status.INFO, "Selecting day of birth");
        signUpPage.selectDateOfBirth("day", "22");

        reportLogger.log(Status.INFO, "Selecting month of birth");
        signUpPage.selectDateOfBirth("month", "November");

        reportLogger.log(Status.INFO, "Selecting year of birth");
        signUpPage.selectDateOfBirth("year", "1990");

        reportLogger.log(Status.INFO, "Checking 'newsletter' & 'special offers' options");
        signUpPage.selectAllRegistrationCheckboxes();

        userData = UserDataProvider.createValidUser();
        signUpPage.setUserInformation(userData);

        reportLogger.log(Status.INFO, "Creating the account");
        accountCreatedPage = signUpPage.clickOnCreateAccountButton();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT CREATED!' title is visible");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking 'Continue' button");
        homePage = accountCreatedPage.clickContinueButton();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Going to 'Cart' page");
        homePage.goToCart();

        reportLogger.log(Status.INFO, "Proceeding to the checkout");
        checkoutPage = cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Verifying 'Address Details' title is visible");
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());

        reportLogger.log(Status.INFO, "Verifying 'Review Your Order' title is visible");
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());

        reportLogger.log(Status.INFO, "Writing a comment");
        checkoutPage.writeAComment("This is an example comment");

        reportLogger.log(Status.INFO, "Placing order");
        paymentPage = checkoutPage.placeOrder();

        reportLogger.log(Status.INFO, "Filling payment information");
        paymentPage.setPaymentDetails(cardData);

        reportLogger.log(Status.INFO, "Confirming order");
        paymentDonePage = paymentPage.confirmOrder();

        reportLogger.log(Status.INFO, "Downloading invoice file");
        paymentDonePage.downloadInvoice();

        reportLogger.log(Status.INFO, "Verifying that the file is downloaded");
        Assert.assertTrue(paymentDonePage.isInvoiceDownloaded());
        reportLogger.log(Status.PASS, "File downloaded");

        reportLogger.log(Status.INFO, "Deleting the account");
        paymentDonePage.deleteAccount();
        reportLogger.log(Status.INFO, "Account deleted");
    }
}
