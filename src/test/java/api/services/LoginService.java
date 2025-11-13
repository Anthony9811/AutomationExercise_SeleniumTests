package api.services;

import api.base.BaseSpec;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginService {

    public JsonPath verifyLogin(String endpoint, String email, String password) {
        Response response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .formParams("email", email, "password", password)
                .when()
                    .post(endpoint)
                .then()
                    .statusCode(200) // Assert common conditions here
                    .extract().response();

        // Handle the bad response format internally
        String jsonString = response.htmlPath().getString("html.body");

        // Return the clean JSON path for assertions in the test
        return new JsonPath(jsonString);
    }
}
