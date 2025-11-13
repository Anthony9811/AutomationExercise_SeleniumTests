package api.login;

import api.services.LoginService;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginApiTests {
    private static String VERIFYLOGIN_ENDPOINT = "/verifyLogin";
    private static String EMAIL_PARAMETER = "doe@testmail.com";
    private static String INVALIDEMAIL_PARAMETER = "test@testmail.com";
    private static String PASSWORD_PARAMETER = "password";
    LoginService loginService = new LoginService();

    @Test(description = "API 7: POST To Verify Login with valid details")
    public void testPostToVerifyLoginWithValidDetails() {
        /*
        MUST EXPLAIN IN THE README THAT I HAD TO USE A SERVICE TO ACCOMPLISH THE DESIRED RESULT
        BECAUSE THE NORMAL APPROACH DOES NOT WORK DUE TO THE RESPONSE'S CONTENT TYPE
         */
        String expectedResponseMessage = "User exists!";

        JsonPath responseJson = loginService.verifyLogin(VERIFYLOGIN_ENDPOINT, EMAIL_PARAMETER, PASSWORD_PARAMETER);
        assertThat(responseJson.getString("message"), equalTo(expectedResponseMessage));
        assertThat(responseJson.getInt("responseCode"), is(200));
    }

    @Test(description = "API 8: POST To Verify Login without email parameter")
    public void testPostToVerifyLoginWithoutEmailParameter() {
        JsonPath responseJson = loginService.verifyLoginWithoutEmail(VERIFYLOGIN_ENDPOINT, EMAIL_PARAMETER);
        String expectedResponseMessage = "Bad request, email or password parameter is missing in POST request.";

        assertThat("The internal response code should be 400",
                responseJson.getInt("responseCode"),
                is(400));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }

    @Test(description = "API 9: DELETE To Verify Login")
    public void testDeleteToVerifyLogin() {
        JsonPath responseJson = loginService.deleteToVerifyLogin(VERIFYLOGIN_ENDPOINT);
        String expectedResponseMessage = "This request method is not supported.";

        assertThat("The internal response code should be 405",
                responseJson.getInt("responseCode"),
                is(405));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }

    @Test(description = "API 10: POST To Verify Login with invalid details")
    public void testPostToVerifyLoginWithInvalidDetails() {
        JsonPath responseJson = loginService.verifyLogin(VERIFYLOGIN_ENDPOINT, INVALIDEMAIL_PARAMETER, PASSWORD_PARAMETER);
        String expectedResponseMessage = "User not found!";

        assertThat("The internal response code should be 404",
                responseJson.getInt("responseCode"),
                is(404));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }
}
