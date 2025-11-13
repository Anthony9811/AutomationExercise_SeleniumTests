package api.brands;

import api.base.BaseSpec;
import api.services.BrandsService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BrandsApiTests {
    private final String BRANDSLIST_ENDPOINT = "/brandsList";
    Response response;
    BrandsService brandsService = new BrandsService();

    @Test(description = "API 3: Get All Brands List")
    public void testGetAllBrandsList() {
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                    .get(BRANDSLIST_ENDPOINT)
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract().response();
    }

    @Test(description = "API 4: PUT To All Brands List")
    public void testPutToAllBrandsList() {
        JsonPath responseJson = brandsService.postToBrands(BRANDSLIST_ENDPOINT);
        String expectedResponseMessage = "This request method is not supported.";

        assertThat("The internal code should be 405",
                responseJson.getInt("responseCode"),
                is(405));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }
}
