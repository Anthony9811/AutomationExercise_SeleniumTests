package api.services;

import api.base.BaseSpec;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BrandsService {

    public JsonPath postToBrands(String endpoint) {
        Response response = (Response) given()
                .spec(BaseSpec.getBaseRequestSpec())
                .when()
                    .post(endpoint)
                .then()
                    .statusCode(201)
                    .extract().response();

        String jsonString = response.htmlPath().getString("html.body");

        return new JsonPath(jsonString);
    }
}
