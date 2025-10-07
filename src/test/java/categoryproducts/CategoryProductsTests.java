package categoryproducts;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryProductsPage;

public class CategoryProductsTests extends BaseTests {

    @Test
    public void testViewCategoryProducts() {
        CategoryProductsPage categoryProductsPage;
        String expectedMenSubcategoryLink = "https://www.automationexercise.com/category_products/6"; //or 5 if TSHIRTS is selected
        String womenCategory = "Dress";
        String menCategory = "Jeans";
        String expectedWomenCategoryMessage = "WOMEN -  " + womenCategory + " PRODUCTS";

        Assert.assertTrue(homePage.areCategoriesVisible());
        categoryProductsPage = homePage.selectWomenSubcategory(womenCategory);

        Assert.assertTrue(categoryProductsPage.isUseOnCategoryProductsPage());
        Assert.assertEquals(categoryProductsPage.getPageTitle(),
                            expectedWomenCategoryMessage,
                            "The page title is incorrect");

        categoryProductsPage.selectMenSubcategory(menCategory);
        Assert.assertEquals(categoryProductsPage.getCurrentCategoryUrl(),
                            expectedMenSubcategoryLink,
                            "The user is not in the " + menCategory + "page");
    }
}
