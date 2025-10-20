package checkout;

import base.BaseTests;
import data.UserDataProvider;
import data.UserInformation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTests extends BaseTests {

    @Test
    public void testVerifyAddressDetailsOnCheckout() {
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

        LoginPage loginPage = homePage.clickOnLoginButton();
        loginPage.setUsername("testname");
        loginPage.setSignupEmail("tau@testmail.com");

        signUpPage = loginPage.signUp();
        signUpPage.selectMrTitle();
        signUpPage.setPassword("password");
        signUpPage.selectDateOfBirth("day", "22");
        signUpPage.selectDateOfBirth("month", "November");
        signUpPage.selectDateOfBirth("year", "1990");
        signUpPage.selectAllRegistrationCheckboxes();
        signUpPage.setUserInformation(userData);

        AccountCreatedPage accountCreatedPage = signUpPage.clickOnCreateAccountButton();
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());

        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.isUserLoggedIn());

        homePage.scrollDown(500);
        homePage.addProductToCart(2);
        cartPage = homePage.viewCart_OnAddedProduct();
        Assert.assertTrue(cartPage.isUserOnCartPage());

        checkoutPage = cartPage.proceedToCheckout();
        Assert.assertEquals(checkoutPage.getDeliveryName(), expectedFullName, "The name does not match");
        Assert.assertEquals(checkoutPage.getDeliveryCompany(), expectedCompany, "The company does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddress(), expectedAddress, "The address does not match");
        Assert.assertEquals(checkoutPage.getDeliveryCountry(), expectedCountry, "The country does not match");
        Assert.assertEquals(checkoutPage.getDeliveryFullAddress(), expectedFullAddress, "The full address does not match");
        Assert.assertEquals(checkoutPage.getDeliveryPhoneNumber(), expectedPhoneNumber, "The phone number does not match");

        deleteAccountPage = checkoutPage.deleteAccount();
        Assert.assertTrue(deleteAccountPage.isAccountDeletedHeaderVisible());
        deleteAccountPage.clickOnContinue();
    }
}
