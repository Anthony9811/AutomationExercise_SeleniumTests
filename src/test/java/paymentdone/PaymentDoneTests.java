package paymentdone;

import base.BaseTests;
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

        homePage.scrollDown(500);
        homePage.addProductToCart(2);
        cartPage = homePage.viewCart_OnAddedProduct();
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        cartPage.proceedToCheckout();
        loginPage = cartPage.goToLogin_OnCheckout();
        loginPage.setUsername("testname");
        loginPage.setSignupEmail("tau@testmail.com");
        signUpPage = loginPage.signUp();

        signUpPage.selectMrTitle();
        signUpPage.setPassword("password");
        signUpPage.selectDateOfBirth("day", "22");
        signUpPage.selectDateOfBirth("month", "November");
        signUpPage.selectDateOfBirth("year", "1990");
        signUpPage.selectAllRegistrationCheckboxes();

        userData = UserDataProvider.createValidUser();
        signUpPage.setUserInformation(userData);
        accountCreatedPage = signUpPage.clickOnCreateAccountButton();
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());

        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.isUserLoggedIn());

        homePage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());

        checkoutPage.writeAComment("This is an example comment");
        paymentPage = checkoutPage.placeOrder();
        paymentPage.setPaymentDetails(cardData);
        paymentDonePage = paymentPage.confirmOrder();
        paymentDonePage.downloadInvoice();
        Assert.assertTrue(paymentDonePage.isInvoiceDownloaded());
        paymentDonePage.deleteAccount();
    }
}
