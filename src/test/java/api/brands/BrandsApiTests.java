package api.brands;

import api.base.BaseSpec;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BrandsApiTests {
    private final String BRANDSLIST_ENDPOINT = "/brandsList";
    Response response;

    @Test(description = "API 3: Get All Brands List")
    public void testGetAllBrandsList() {
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                .get(BRANDSLIST_ENDPOINT)
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200, "The response code does not match the expected");
    }

    @Test(description = "API 4: PUT To All Brands List")
    public void testPutToAllBrandsList() {
        /*
        GEMINI NOTES:
        If this still returns 200, it confirms that the AutomationExercise API is designed to return the product data
        for virtually any method on that endpoint,
        and the 405 you see in Postman is only triggered under a very specific, near-zero-content scenario
        that Rest Assured cannot perfectly replicate due to its internal request building defaults.
        In that final case, you must choose to test the actual observed behavior (200) over the incorrect documentation.
         */
        String requestBody = "{\n" +
                " \"id\": \"44\",\n" +
                " \"brand\": \"NFL\"\n" +
                "}";

        response = (Response) given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                .request(Method.PUT, BRANDSLIST_ENDPOINT)
                .then()
                .statusCode(405)
                .body("responseCode", equalTo(405));
    }
}
