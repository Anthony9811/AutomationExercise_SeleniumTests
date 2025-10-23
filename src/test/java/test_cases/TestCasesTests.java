package test_cases;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestCasesPage;

public class TestCasesTests extends BaseTests {

    @Test
    public void testVerifyTestCasesPage() {
        reportLogger = report.createTest("Test Case 7: Verify Test Cases Page");

        reportLogger.log(Status.INFO, "Going to 'Test Cases' page");
        TestCasesPage testCasesPage = homePage.goToTestCases();

        reportLogger.log(Status.INFO, "Verifying that the user is navigated to test cases page");
        Assert.assertTrue(testCasesPage.isUserOnTestCasesPage());
    }
}
