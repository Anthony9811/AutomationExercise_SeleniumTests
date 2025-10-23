package login;

import base.BaseTests;
import com.aventstack.extentreports.Status;
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
        reportLogger = report.createTest("Test Case 1: Register User");

        reportLogger.log(Status.INFO, "Clicking on 'Signup/Login' button");
        loginPage = homePage.clickOnLoginButton();

        reportLogger.log(Status.INFO, "Verifying 'New User Signup!' title is visible");
        Assert.assertTrue(loginPage.isSignUpTextVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Typing username");
        loginPage.setUsername("testname");

        reportLogger.log(Status.INFO, "Typing email");
        loginPage.setSignupEmail("tau@testmail.com");

        reportLogger.log(Status.INFO, "Clicking 'Signup' button");
        signUpPage = loginPage.signUp();

        reportLogger.log(Status.INFO, " Verifying that 'ENTER ACCOUNT INFORMATION' title is visible");
        Assert.assertTrue(signUpPage.isAccountInformationHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

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

        reportLogger.log(Status.INFO, "Clicking the 'create account' button");
        accountCreatedPage = signUpPage.clickOnCreateAccountButton();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT CREATED!' title is visible");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking the 'Continue' button");
        homePage = accountCreatedPage.clickContinueButton();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Deleting the account");
        deleteAccountPage = homePage.deleteAccount();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT DELETED!' title is visible");
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking the continue button");
        deleteAccountPage.clickOnContinue();
    }

    @Test
    public void testCorrectUserLogin() {
        reportLogger = report.createTest("Test Case 1: Register User");

        reportLogger.log(Status.INFO, "Clicking on 'Signup/Login' button");
        loginPage = homePage.clickOnLoginButton();

        reportLogger.log(Status.INFO, "Verifying 'Login to your account' title is visible");
        Assert.assertTrue(loginPage.isLoginTextVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Typing email");
        loginPage.setLoginEmail("doe@testmail.com");

        reportLogger.log(Status.INFO, "Typing password");
        loginPage.setLoginPassword("password");

        reportLogger.log(Status.INFO, "Clicking 'Login' button");
        loginPage.login();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Clicking 'Logout' button");
        homePage.logout();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is no longer visible");
        Assert.assertFalse(homePage.isUserLoggedOut());
        reportLogger.log(Status.PASS, "The tag is no longer visible");
    }

    @Test
    public void testIncorrectUserLogin() {
        reportLogger = report.createTest("Test Case 3: Login User with incorrect email and password");

        String expectedError = "Your email or password is incorrect!";

        reportLogger.log(Status.INFO, "Clicking on 'Signup/Login' button");
        loginPage = homePage.clickOnLoginButton();

        reportLogger.log(Status.INFO, "Verifying 'Login to your account' title is visible");
        Assert.assertTrue(loginPage.isLoginTextVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Typing an unregistered email");
        loginPage.setLoginEmail("joe@email.com");

        reportLogger.log(Status.INFO, "Typing a password");
        loginPage.setLoginPassword("password123");

        reportLogger.log(Status.INFO, "Clicking the 'Login' button");
        loginPage.login();

        reportLogger.log(Status.INFO, "Verifying error 'Your email or password is incorrect!' is visible");
        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                expectedError,
                "Error message is incorrect");
        reportLogger.log(Status.PASS, "The error message is visible");
    }

    @Test
    public void testExistingEmailRegistration() {
        reportLogger = report.createTest("Test Case 5: Register User with existing email");

        reportLogger.log(Status.INFO, "Clicking on 'Signup/Login' button");
        loginPage = homePage.clickOnLoginButton();

        reportLogger.log(Status.INFO, "Verifying 'New User Signup!' title is visible");
        Assert.assertTrue(loginPage.isSignUpTextVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Typing username");
        loginPage.setUsername("Testname");

        reportLogger.log(Status.INFO, "Typing email");
        loginPage.setSignupEmail("doe@testmail.com");

        reportLogger.log(Status.INFO, "Clicking 'Signup' button");
        loginPage.signUp();

        reportLogger.log(Status.INFO, "Verifying error message 'Email Address already exist!' is visible");
        String expectedError = "Email Address already exist!";
        Assert.assertEquals(loginPage.getSignUpErrorMessage(),
                expectedError,
                "Error message is incorrect");
        reportLogger.log(Status.PASS, "The error message is visible");
    }

    @Test
    public void testRegisterWhileCheckout() {
        reportLogger = report.createTest("Test Case 14: Place Order: Register while Checkout");

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        homePage.scrollDown(500);
        homePage.addProductToCart(2);

        reportLogger.log(Status.INFO, "Clicking on 'View Product' button");
        cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Verifying that the cart page is displayed");
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        reportLogger.log(Status.INFO, "Proceeding to checkout");
        cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Clicking on the 'login' button");
        loginPage = cartPage.goToLogin_OnCheckout();

        reportLogger.log(Status.INFO, "Typing a username");
        loginPage.setUsername("testname");

        reportLogger.log(Status.INFO, "Typing an email");
        loginPage.setSignupEmail("tau@testmail.com");

        reportLogger.log(Status.INFO, "Clicking the 'Signup' button");
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

        reportLogger.log(Status.INFO, "Clicking the 'Continue' button");
        homePage = accountCreatedPage.clickContinueButton();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Going to the Cart page");
        homePage.goToCart();

        reportLogger.log(Status.INFO, "Proceeding to checkout");
        checkoutPage = cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Verifying that the 'Address Details' title is visible");
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Verifying that the 'Review Your Order' title is visible");
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Typing a comment");
        checkoutPage.writeAComment("This is an example comment");

        reportLogger.log(Status.INFO, "Clicking the 'Place Order' button");
        paymentPage = checkoutPage.placeOrder();

        reportLogger.log(Status.INFO, "Typing payment information");
        paymentPage.setPaymentDetails(cardData);

        reportLogger.log(Status.INFO, "Confirming order");
        paymentDonePage = paymentPage.confirmOrder();

        reportLogger.log(Status.INFO, "Deleting the account");
        deleteAccountPage = paymentDonePage.clickOnDeleteAccount();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT DELETED!' title is visible");
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking 'Continue' button");
        deleteAccountPage.clickOnContinue();
    }

    @Test
    public void testRegisterBeforeCheckout() {
        reportLogger = report.createTest("Test Case 15: Place Order: Register before Checkout");

        reportLogger.log(Status.INFO, "Clicking on 'Signup/Login' button");
        loginPage = homePage.clickOnLoginButton();

        reportLogger.log(Status.INFO, "Typing username");
        loginPage.setUsername("testname");

        reportLogger.log(Status.INFO, "Typing email");
        loginPage.setSignupEmail("tau@testmail.com");

        reportLogger.log(Status.INFO, "Clicking 'Signup' button");
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

        reportLogger.log(Status.INFO, "Clicking the 'create account' button");
        accountCreatedPage = signUpPage.clickOnCreateAccountButton();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT CREATED!' title is visible");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking the 'Continue' button");
        homePage = accountCreatedPage.clickContinueButton();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Adding product to the cart");
        homePage.scrollDown(500);
        homePage.addProductToCart(2);

        reportLogger.log(Status.INFO, "Clicking the 'View Cart' button");
        cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Verifying that cart page is displayed");
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        reportLogger.log(Status.INFO, "Proceeding to checkout");
        checkoutPage = cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Verifying 'Address Details' title is visible");
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());

        reportLogger.log(Status.INFO, "Verifying 'Review Your Order' title is visible");
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());

        reportLogger.log(Status.INFO, "Writing a comment");
        checkoutPage.writeAComment("This is an example comment");

        reportLogger.log(Status.INFO, "Placing order");
        paymentPage = checkoutPage.placeOrder();

        reportLogger.log(Status.INFO, "Typing payment information");
        paymentPage.setPaymentDetails(cardData);

        reportLogger.log(Status.INFO, "Confirming order");
        paymentDonePage = paymentPage.confirmOrder();

        reportLogger.log(Status.INFO, "Deleting the account");
        deleteAccountPage = paymentDonePage.clickOnDeleteAccount();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT DELETED!' title is visible");
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking the continue button");
        deleteAccountPage.clickOnContinue();
    }

    @Test
    public void testLoginBeforeCheckOut() {
        reportLogger = report.createTest("Test Case 16: Place Order: Login before Checkout");

        reportLogger.log(Status.INFO, "Clicking on 'Signup/Login' button");
        loginPage = homePage.clickOnLoginButton();

        reportLogger.log(Status.INFO, "Typing login email");
        loginPage.setLoginEmail("doe@testmail.com");

        reportLogger.log(Status.INFO, "Typing login password");
        loginPage.setLoginPassword("password");

        reportLogger.log(Status.INFO, "Clicking 'Login' button");
        loginPage.login();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Scrolling down to the products");
        homePage.scrollDown(500);

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        homePage.addProductToCart(2);

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        homePage.addProductToCart(3);

        reportLogger.log(Status.INFO, "Clicking the 'View Cart' button");
        cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Verifying that cart page is displayed");
        Assert.assertEquals(cartPage.getUrl(), cartUrl, "Cart page is not being displayed");

        reportLogger.log(Status.INFO, "Proceeding to checkout");
        checkoutPage = cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Verifying 'Address Details' title is visible");
        Assert.assertTrue(checkoutPage.isAddressDetailsHeaderVisible());

        reportLogger.log(Status.INFO, "Verifying 'Review Your Order' title is visible");
        Assert.assertTrue(checkoutPage.isReviewYourOrderHeaderVisible());

        reportLogger.log(Status.INFO, "Writing a comment");
        checkoutPage.writeAComment("This is an example comment");

        reportLogger.log(Status.INFO, "Placing order");
        paymentPage = checkoutPage.placeOrder();

        reportLogger.log(Status.INFO, "Typing payment information");
        paymentPage.setPaymentDetails(cardData);

        reportLogger.log(Status.INFO, "Confirming order");
        paymentDonePage = paymentPage.confirmOrder();

        reportLogger.log(Status.INFO, "Verifying 'Your order has been placed successfully!' message is visible");
        Assert.assertTrue(paymentDonePage.isConfirmationMessageVisible());
        reportLogger.log(Status.PASS, "The message is visible");

        reportLogger.log(Status.INFO, "Clicking the 'Continue' button");
        paymentDonePage.clickOnContinue();

        reportLogger.log(Status.INFO, "Logging out");
        homePage.logout();

        reportLogger.log(Status.INFO, "Verifying that the user is logged out");
        Assert.assertFalse(homePage.isUserLoggedOut());
        reportLogger.log(Status.PASS, "The user is logged out");
    }
}
