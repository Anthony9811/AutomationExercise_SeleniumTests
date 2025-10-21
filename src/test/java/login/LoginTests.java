package login;

import base.BaseTests;
import data.PaymentData;
import data.PaymentDataProvider;
import data.UserDataProvider;
import data.UserInformation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class LoginTests extends BaseTests {
    LoginPage loginPage;
    UserInformation userData;
    SignUpPage signUpPage;
    AccountCreatedPage accountCreatedPage;
    DeleteAccountPage deleteAccountPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    PaymentDonePage paymentDonePage;
    PaymentData cardData = PaymentDataProvider.getTestCardInformation();
    String cartUrl = "https://www.automationexercise.com/view_cart";

    @Test
    public void testUserRegistration() {
        loginPage = homePage.clickOnLoginButton();

        Assert.assertTrue(loginPage.isSignUpTextVisible());

        loginPage.setUsername("testname");
        loginPage.setSignupEmail("tau@testmail.com");

        signUpPage = loginPage.signUp();
        Assert.assertTrue(signUpPage.isAccountInformationHeaderVisible());

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

        deleteAccountPage = homePage.deleteAccount();
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        deleteAccountPage.clickOnContinue();
    }

    @Test
    public void testCorrectUserLogin() {
        loginPage = homePage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isLoginTextVisible());

        loginPage.setLoginEmail("doe@testmail.com");
        loginPage.setLoginPassword("password");
        loginPage.login();
        Assert.assertTrue(homePage.isUserLoggedIn());

        homePage.logout();
        Assert.assertFalse(homePage.isUserLoggedOut());
    }

    @Test
    public void testIncorrectUserLogin() {
        loginPage = homePage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isLoginTextVisible());

        loginPage.setLoginEmail("joe@email.com");
        loginPage.setLoginPassword("password123");
        loginPage.login();

        String expectedError = "Your email or password is incorrect!";
        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                            expectedError,
                            "Error message is incorrect");
    }

    @Test
    public void testExistingEmailRegistration() {
        loginPage = homePage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isSignUpTextVisible());

        loginPage.setUsername("Testname");
        loginPage.setSignupEmail("doe@testmail.com");
        loginPage.signUp();

        String expectedError = "Email Address already exist!";
        Assert.assertEquals(loginPage.getSignUpErrorMessage(),
                expectedError,
                "Error message is incorrect");
    }

    @Test
    public void testRegisterWhileCheckout() {
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

        deleteAccountPage = paymentDonePage.clickOnDeleteAccount();
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        deleteAccountPage.clickOnContinue();
    }

    @Test
    public void testRegisterBeforeCheckout() {
        loginPage = homePage.clickOnLoginButton();
        loginPage.setUsername("testname");
        loginPage.setSignupEmail("tau@testmail.com");
        signUpPage = loginPage.signUp();
        Assert.assertTrue(signUpPage.isAccountInformationHeaderVisible());

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

        homePage.scrollDown(500);
        homePage.addProductToCart(2);
        cartPage = homePage.viewCart_OnAddedProduct();
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        checkoutPage = cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());

        checkoutPage.writeAComment("This is an example comment");
        paymentPage = checkoutPage.placeOrder();
        paymentPage.setPaymentDetails(cardData);
        paymentDonePage = paymentPage.confirmOrder();

        deleteAccountPage = paymentDonePage.clickOnDeleteAccount();
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        deleteAccountPage.clickOnContinue();
    }

    @Test
    public void testLoginBeforeCheckOut() {
        loginPage = homePage.clickOnLoginButton();
        loginPage.setLoginEmail("doe@testmail.com");
        loginPage.setLoginPassword("password");
        loginPage.login();
        Assert.assertTrue(homePage.isUserLoggedIn());

        homePage.scrollDown(500);
        homePage.addProductToCart(2);
        homePage.addProductToCart(3);
        cartPage = homePage.viewCart_OnAddedProduct();
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        checkoutPage = cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());

        checkoutPage.writeAComment("This is an example comment");
        paymentPage = checkoutPage.placeOrder();
        paymentPage.setPaymentDetails(cardData);
        paymentDonePage = paymentPage.confirmOrder();

        Assert.assertTrue(paymentDonePage.isConfirmationMessageVisible());
        paymentDonePage.clickOnContinue();
        homePage.logout();
        Assert.assertFalse(homePage.isUserLoggedOut());
    }
}
