package api.account;

import api.base.BaseSpec;
import api.services.AccountsService;
import data.UserDataProvider;
import data.UserInformation;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AccountActionsApiTests {
    private final String CREATEACCOUNT_ENDPOINT = "/createAccount";
    private final String DELETEACCOUNT_ENDPOINT = "/deleteAccount";
    private final String UPDATEACCOUNT_ENDPOINT = "/updateAccount";
    private final String GETACCOUNT_ENDPOINT = "/getUserDetailByEmail";

    AccountsService accountsService = new AccountsService();
    Map<String, Object> payload = new HashMap<>();
    JsonPath responseJson;
    String birthDay = "22";
    String birthMonth = "November";
    String birthYear = "1990";

    @Test(description = "API 11: POST To Create/Register User Account")
    public void testPostToCreateUserAccount() {
        birthDay = "22";
        birthMonth = "November";
        birthYear = "1990";
        String expectedResponseMessage = "User created!";

        UserInformation user = UserDataProvider.createValidUser();
        payload.put("name", user.firstName + " " + user.lastName);
        payload.put("email", "tau@testmail.com");
        payload.put("password", "password");
        payload.put("title", "Mr");
        payload.put("birth_date", birthDay);
        payload.put("birth_month", birthMonth);
        payload.put("birth_year", birthYear);
        payload.put("firstname", user.firstName);
        payload.put("lastname", user.lastName);
        payload.put("company", user.company);
        payload.put("address1", user.address);
        payload.put("address2", user.address);
        payload.put("country", user.country);
        payload.put("zipcode", user.zipCode);
        payload.put("state", user.state);
        payload.put("city", user.city);
        payload.put("mobile_number", user.mobileNumber);

        responseJson = accountsService.createUserAccount(CREATEACCOUNT_ENDPOINT, payload);

        assertThat("The internal response code should be 201",
                responseJson.getInt("responseCode"),
                is(201));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }

    @Test(description = "API 13: PUT METHOD To Update User Account")
    public void testPutMethodToUpdateUserAccount() {
        birthDay = "11";
        birthMonth = "May";
        birthYear = "1998";
        String expectedResponseMessage = "User updated!";

        UserInformation user = UserDataProvider.createValidUser();
        payload.put("name", user.firstName + " " + user.lastName);
        payload.put("email", "tau@testmail.com");
        payload.put("password", "password");
        payload.put("title", "Mr");
        payload.put("birth_date", birthDay);
        payload.put("birth_month", birthMonth);
        payload.put("birth_year", birthYear);
        payload.put("firstname", user.firstName);
        payload.put("lastname", user.lastName);
        payload.put("company", user.company);
        payload.put("address1", user.address);
        payload.put("address2", user.address);
        payload.put("country", user.country);
        payload.put("zipcode", user.zipCode);
        payload.put("state", user.state);
        payload.put("city", user.city);
        payload.put("mobile_number", user.mobileNumber);

        responseJson = accountsService.updateUserAccount(UPDATEACCOUNT_ENDPOINT, payload);
        assertThat("The internal response code should be 200",
                responseJson.getInt("responseCode"),
                is(200));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }

    @Test(description = "API 12: DELETE METHOD To Delete User Account")
    public void testDeleteMethodToDeleteUserAccount() {
        String email = "tau@testmail.com";
        String password = "password";
        String expectedResponseMessage = "Account deleted!";

        payload.put("email", email);
        payload.put("password", password);

        responseJson = accountsService.deleteUserAccount(DELETEACCOUNT_ENDPOINT, payload);
        assertThat("The internal response code should be 200",
                responseJson.getInt("responseCode"),
                is(200));

        assertThat("Error message check",
                responseJson.getString("message"),
                equalTo(expectedResponseMessage));
    }

    @Test(description = "API 14: GET user account detail by email")
    public void testGetUserAccountDetailByEmail() {
        String email = "doe@testmail.com";

        responseJson = accountsService.getUserDetailByEmail(GETACCOUNT_ENDPOINT, email);
        assertThat("The internal response code should be 200",
                responseJson.getInt("responseCode"),
                is(200));

        assertThat("Checking user's name",
                responseJson.getString("user.name"),
                equalTo("John"));

    }
}
