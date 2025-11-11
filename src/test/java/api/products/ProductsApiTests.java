package api.products;

import api.base.BaseSpec;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsApiTests {
    private final String PRODUCTSLIST_ENDPOINT = "/productsList";
    private final String SEARCHPRODUCT_ENDPOINT = "/searchProduct";
    final String SEARCH_TERM = "top";
    Response response;

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
        //IT FAILS DUE TO AN API ERROR
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                .post(PRODUCTSLIST_ENDPOINT)
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 405, "The response message does not match the expected");
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
        //IT FAILS DUE TO AN API ERROR
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                .post(SEARCHPRODUCT_ENDPOINT)
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 400, "The response message does not match the expected");
    }
}
