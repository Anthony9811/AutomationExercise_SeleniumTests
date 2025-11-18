package api.services;

import api.base.BaseSpec;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AccountsService {

    public JsonPath createUserAccount(String endpoint, Map<String, Object> payload) {
        return executeRequest(Method.POST, endpoint, payload, 200);
    }

    public JsonPath deleteUserAccount(String endpoint, Map<String, Object> payload) {
        return executeRequest(Method.DELETE, endpoint, payload, 200);
    }

    public JsonPath updateUserAccount(String endpoint, Map<String, Object> payload) {
        return executeRequest(Method.PUT, endpoint, payload, 200);
    }

    public JsonPath getUserDetailByEmail(String endpoint, String email) {
        Response response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .queryParam("email", email)
                .when()
                    .get(endpoint)
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract().response();

        String jsonString = response.htmlPath().getString("html.body");

        return new JsonPath(jsonString);
    }

    private JsonPath executeRequest(Method method, String endpoint, Map<String, Object> payload, int expectedStatusCode) {
        Response response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .formParams(payload)
                .when()
                    .request(method, endpoint)
                .then()
                    .statusCode(expectedStatusCode)
                    .extract().response();

        String jsonString = response.htmlPath().getString("html.body");

        return new JsonPath(jsonString);
    }
}
