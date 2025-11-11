package ui.contact_us;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;

public class ContactUsTests extends BaseTests {

    @Test
    public void testFillContactUsForm() {
        reportLogger = report.createTest("Test Case 6: Contact Us Form");

        reportLogger.log(Status.INFO, "Clicking on 'Contact Us' button");
        ContactUsPage contactUsPage = homePage.goToContactUs();

        String relativeFilePath = "src/main/resources/image.jpg";
        String expectedSuccessMessage = "Success! Your details have been submitted successfully.";

        reportLogger.log(Status.INFO, "Verifying 'GET IN TOUCH' title is visible");
        Assert.assertTrue(contactUsPage.isFormHeaderVisible());
        reportLogger.log(Status.PASS, "The title is visible");

        reportLogger.log(Status.INFO, "Typing Name");
        contactUsPage.setName("John");

        reportLogger.log(Status.INFO, "Typing Email");
        contactUsPage.setEmail("doe@testmail.com");

        reportLogger.log(Status.INFO, "Typing Subject");
        contactUsPage.setSubject("Test Subject");

        reportLogger.log(Status.INFO, "Typing Message");
        contactUsPage.setMessage("This is a test message!");

        reportLogger.log(Status.INFO, "Uploading file");
        contactUsPage.uploadFile(relativeFilePath);

        reportLogger.log(Status.INFO, "Accepting the alert");
        contactUsPage.alert_clickToAccept();
        reportLogger.log(Status.PASS, "Alert accepted");

        reportLogger.log(Status.INFO, "Verifying 'Success! Your details have been submitted successfully.' message is visible");
        Assert.assertEquals(contactUsPage.getSubmitStatusMessage(),
                            expectedSuccessMessage,
                            "The submit was unsuccessful");
        reportLogger.log(Status.PASS, "The message is visible");

        reportLogger.log(Status.PASS, "Going to home page");
        contactUsPage.goToHome();

        reportLogger.log(Status.INFO, "Verifying that the home page is in view");
        Assert.assertTrue(contactUsPage.isHomePageInView());
        reportLogger.log(Status.PASS, "The home page is in view");
    }
}
