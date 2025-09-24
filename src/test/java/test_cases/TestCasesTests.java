package test_cases;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestCasesPage;

public class TestCasesTests extends BaseTests {

    @Test
    public void testVerifyTestCasesPage() {
        TestCasesPage testCasesPage = homePage.clickOnTestCases();

        Assert.assertTrue(testCasesPage.isUserOnTestCasesPage());
    }
}
