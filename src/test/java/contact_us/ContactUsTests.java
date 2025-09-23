package contact_us;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;

public class ContactUsTests extends BaseTests {

    @Test
    public void testFillContactUsForm() {
        ContactUsPage contactUsPage = homePage.clickOnContactUs();
        String relativeFilePath = "src/main/resources/image.jpg";
        String expectedSuccessMessage = "Success! Your details have been submitted successfully.";

        Assert.assertTrue(contactUsPage.isFormHeaderVisible());

        contactUsPage.setName("John");
        contactUsPage.setEmail("doe@testmail.com");
        contactUsPage.setSubject("Test Subject");
        contactUsPage.setMessage("This is a test message!");
        contactUsPage.uploadFile(relativeFilePath);
        contactUsPage.alert_clickToAccept();

        Assert.assertEquals(contactUsPage.getSubmitStatusMessage(),
                            expectedSuccessMessage,
                            "The submit was unsuccessful");

        contactUsPage.goToHome();

        Assert.assertTrue(contactUsPage.isHomePageInView());
    }
}
