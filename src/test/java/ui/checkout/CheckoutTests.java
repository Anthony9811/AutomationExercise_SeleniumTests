package ui.checkout;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import data.UserDataProvider;
import data.UserInformation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTests extends BaseTests {

    @Test
    public void testVerifyAddressDetailsOnCheckout() {
        reportLogger = report.createTest("Test Case 23: Verify Address Details In Checkout Page");

        SignUpPage signUpPage;
        UserInformation userData;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        DeleteAccountPage deleteAccountPage;
        userData = UserDataProvider.createValidUser();
        String expectedFullName = "Mr. " + userData.firstName + " " + userData.lastName;
        String expectedCompany = userData.company;
        String expectedAddress = userData.address;
        String expectedCountry = userData.country;
        String expectedFullAddress = userData.city + " " + userData.state + " " + userData.zipCode;
        String expectedPhoneNumber = userData.mobileNumber;

        reportLogger.log(Status.INFO, "Clicking on login");
        LoginPage loginPage = homePage.clickOnLoginButton();

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
        signUpPage.setUserInformation(userData);

        reportLogger.log(Status.INFO, "Clicking the 'create account' button");
        AccountCreatedPage accountCreatedPage = signUpPage.clickOnCreateAccountButton();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT CREATED!' title is visible");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking the 'Continue' button");
        homePage = accountCreatedPage.clickContinueButton();

        reportLogger.log(Status.INFO, "Verifying that 'Logged in as <<username>>' tag is visible");
        Assert.assertTrue(homePage.isUserLoggedIn());
        reportLogger.log(Status.PASS, "The tag is visible");

        reportLogger.log(Status.INFO, "Scrolling down");
        homePage.scrollDown(500);

        reportLogger.log(Status.INFO, "Adding a product to the cart");
        homePage.addProductToCart(2);

        reportLogger.log(Status.INFO, "Clicking the 'View Cart' button");
        cartPage = homePage.viewCart_OnAddedProduct();

        reportLogger.log(Status.INFO, "Verifying that the user is in the Cart page");
        Assert.assertTrue(cartPage.isUserOnCartPage());

        reportLogger.log(Status.INFO, "Proceeding to the checkout");
        checkoutPage = cartPage.proceedToCheckout();

        reportLogger.log(Status.INFO, "Verifying the delivery name");
        Assert.assertEquals(checkoutPage.getDeliveryName(), expectedFullName, "The name does not match");
        reportLogger.log(Status.PASS, "The name is correct");

        reportLogger.log(Status.INFO, "Verifying the delivery company");
        Assert.assertEquals(checkoutPage.getDeliveryCompany(), expectedCompany, "The company does not match");
        reportLogger.log(Status.PASS, "The company is correct");

        reportLogger.log(Status.INFO, "Verifying the delivery adrress");
        Assert.assertEquals(checkoutPage.getDeliveryAddress(), expectedAddress, "The address does not match");
        reportLogger.log(Status.PASS, "The address is correct");

        reportLogger.log(Status.INFO, "Verifying the delivery country");
        Assert.assertEquals(checkoutPage.getDeliveryCountry(), expectedCountry, "The country does not match");
        reportLogger.log(Status.PASS, "The country is correct");

        reportLogger.log(Status.INFO, "Verifying the delivery full address");
        Assert.assertEquals(checkoutPage.getDeliveryFullAddress(), expectedFullAddress, "The full address does not match");
        reportLogger.log(Status.PASS, "The full address is correct");

        reportLogger.log(Status.INFO, "Verifying the delivery phone number");
        Assert.assertEquals(checkoutPage.getDeliveryPhoneNumber(), expectedPhoneNumber, "The phone number does not match");
        reportLogger.log(Status.PASS, "The phone number is correct");

        reportLogger.log(Status.INFO, "Deleting the account");
        deleteAccountPage = checkoutPage.deleteAccount();

        reportLogger.log(Status.INFO, "Verifying that the 'ACCOUNT DELETED!' title is visible");
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Clicking the continue button");
        deleteAccountPage.clickOnContinue();
    }
}
