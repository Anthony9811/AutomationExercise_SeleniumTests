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

    @Test
    public void testUserRegistration() {
        LoginPage loginPage = homePage.clickLoginButton();

        Assert.assertTrue(loginPage.isSignUpTextVisible());

        loginPage.setUsername("testname");
        loginPage.setEmailField("tau@testmail.com");

        SignUpPage signUpPage = loginPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isAccountInformationHeaderVisible());

        signUpPage.selectMrTitle();

        signUpPage.setPassword("password");

        signUpPage.selectDateOfBirth("day", "22");
        signUpPage.selectDateOfBirth("month", "November");
        signUpPage.selectDateOfBirth("year", "1990");

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
}
