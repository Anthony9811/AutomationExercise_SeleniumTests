package login;

import base.BaseTests;
import data.UserInformation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountCreatedPage;
import pages.DeleteAccountPage;
import pages.LoginPage;
import pages.SignUpPage;

public class LoginTests extends BaseTests {
    LoginPage loginPage;

    @Test
    public void testUserRegistration() {
        loginPage = homePage.clickOnLoginButton();

        Assert.assertTrue(loginPage.isSignUpTextVisible());

        loginPage.setUsername("testname");
        loginPage.setSignupEmail("tau@testmail.com");

        SignUpPage signUpPage = loginPage.signUp();
        Assert.assertTrue(signUpPage.isAccountInformationHeaderVisible());

        signUpPage.selectMrTitle();

        signUpPage.setPassword("password");

        signUpPage.selectDateOfBirth("day", "22");
        signUpPage.selectDateOfBirth("month", "November");
        signUpPage.selectDateOfBirth("year", "1990");

        signUpPage.scrollIntoView(signUpPage.firstNameField);
        signUpPage.selectAllRegistrationCheckboxes();

        UserInformation userData = new UserInformation(
                "Mark",
                "Calaway",
                "Dead Man Inc",
                "Death Valley, WWE",
                "United States",
                "Connecticut",
                "Stamford",
                "50301",
                "12345678"
        );
        signUpPage.setUserInformation(userData);
        signUpPage.scrollIntoView(signUpPage.createAccountButton);

        AccountCreatedPage accountCreatedPage = signUpPage.clickOnCreateAccountButton();
        Assert.assertTrue(accountCreatedPage.isAccountCreatedHeaderVisible());

        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.isUserLoggedIn());

        DeleteAccountPage deleteAccountPage = homePage.clickOnDeleteAccount();
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

        homePage.clickOnLogoutButton();
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
}
