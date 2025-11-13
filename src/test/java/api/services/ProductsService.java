package api.services;

import api.base.BaseSpec;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ProductsService {
    Response response;

    public JsonPath postToProducts(String endpoint) {
        response = given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                    .post(endpoint)
                .then()
                    .statusCode(200)
                .extract().response();

        String jsonString = response.htmlPath().getString("html.body");

        return new JsonPath(jsonString);
    }
}
