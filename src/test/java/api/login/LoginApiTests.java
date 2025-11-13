package api.login;

import api.services.LoginService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginApiTests {
    private static String VERIFYLOGIN_ENDPOINT = "/verifyLogin";
    private static String EMAIL_PARAMETER = "doe@testmail.com";
    private static String PASSWORD_PARAMETER = "password";

    @Test(description = "API 7: POST To Verify Login with valid details")
    public void testPostToVerifyLoginWithValidDetails() {
        /*
        MUST EXPLAIN IN THE README THAT I HAD TO USE A SERVICE TO ACCOMPLISH THE DESIRED RESULT
        BECAUSE THE NORMAL APPROACH DOES NOT WORK DUE TO THE RESPONSE'S CONTENT TYPE
         */
        String expectedResponseMessage = "User exists!";
        LoginService loginService = new LoginService();

        JsonPath responseJson = loginService.verifyLogin(VERIFYLOGIN_ENDPOINT, EMAIL_PARAMETER, PASSWORD_PARAMETER);
        assertThat(responseJson.getString("message"), equalTo(expectedResponseMessage));
        assertThat(responseJson.getInt("responseCode"), is(200));
    }
}
