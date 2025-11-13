package api.products;

import api.base.BaseSpec;
import api.services.ProductsService;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductsApiTests {
    private final String PRODUCTSLIST_ENDPOINT = "/productsList";
    private final String SEARCHPRODUCT_ENDPOINT = "/searchProduct";
    final String SEARCH_TERM = "top";
    Response response;
    ProductsService productsService = new ProductsService();

    @Test(description = "API 1: Returns a complete list of all the products")
    public void testGetAllProductsList() {
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                .get(PRODUCTSLIST_ENDPOINT)
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200, "The response message does not match the expected");
    }

    @Test(description = "API 2: Post to all products list")
    public void testPostToAllProductsList() {
        JsonPath responseJson = productsService.postToProducts(PRODUCTSLIST_ENDPOINT);
        String expectedResponseMessage = "This request method is not supported.";

        assertThat("The internal code should be 405",
                responseJson.getInt("responseCode"),
                is(405));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }

    @Test(description = "API 5: Post to search product")
    public void testPostToSearchProduct() {
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .formParam("search_product", SEARCH_TERM)
                .when()
                .post(SEARCHPRODUCT_ENDPOINT)
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200, "The response message does not match the expected");
    }

    @Test(description = "API 6: Search product without parameter")
    public void testPostToSearchProductWithoutParameter() {
        JsonPath responseJson = productsService.postToProducts(SEARCHPRODUCT_ENDPOINT);
        String expectedResponseMessage = "Bad request, search_product parameter is missing in POST request.";

        assertThat("The internal code should be 400",
                responseJson.getInt("responseCode"),
                is(400));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }
}
