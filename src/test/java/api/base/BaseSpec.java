package api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseSpec {
    private static final String BASE_URL = "https://automationexercise.com/api";

    public static RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }
}
