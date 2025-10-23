package categoryproducts;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryProductsPage;

public class CategoryProductsTests extends BaseTests {

    @Test
    public void testViewCategoryProducts() {
        reportLogger = report.createTest("Test Case 18: View Category Products");

        CategoryProductsPage categoryProductsPage;
        String expectedMenSubcategoryLink = "https://www.automationexercise.com/category_products/6"; //or 5 if TSHIRTS is selected
        String womenCategory = "Dress";
        String menCategory = "Jeans";
        String expectedWomenCategoryMessage = "WOMEN -  " + womenCategory.toUpperCase() + " PRODUCTS";

        reportLogger.log(Status.INFO, "Verifying that categories are visible on the left side bar");
        Assert.assertTrue(homePage.areCategoriesVisible());
        reportLogger.log(Status.PASS, "Categories are visible");

        reportLogger.log(Status.INFO, "Clicking on the 'Women' category");
        reportLogger.log(Status.INFO, "Clicking on the 'Dress' subcategory");
        categoryProductsPage = homePage.selectWomenSubcategory(womenCategory);

        reportLogger.log(Status.INFO, "Verifying that the user is on the selected category page");
        Assert.assertTrue(categoryProductsPage.isUseOnCategoryProductsPage());
        reportLogger.log(Status.INFO, "The user is on the selected category page");

        reportLogger.log(Status.INFO, "Verifying that the page title is WOMEN - TOPS PRODUCTS");
        Assert.assertEquals(categoryProductsPage.getPageTitle().toUpperCase(),
                            expectedWomenCategoryMessage,
                            "The page title is incorrect");
        reportLogger.log(Status.PASS, "The title is correct");

        reportLogger.log(Status.INFO, "Clicking on the 'Men' category");
        reportLogger.log(Status.INFO, "Clicking on the 'Jeans' subcategory");
        categoryProductsPage.selectMenSubcategory(menCategory);

        reportLogger.log(Status.INFO, "Verifying that the user is on the Jeans subcategory page");
        Assert.assertEquals(categoryProductsPage.getCurrentCategoryUrl(),
                            expectedMenSubcategoryLink,
                            "The user is not in the " + menCategory + "page");
    }
}
