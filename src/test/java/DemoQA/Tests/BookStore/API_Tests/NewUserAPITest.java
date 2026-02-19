package DemoQA.Tests.BookStore.API_Tests;

import DemoQA.data.Pages.BookStore.LogInPage;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static DemoQA.Helpers.RandomAlphaNumeric.randomAlphaNumeric;
import static io.restassured.RestAssured.given;

public class NewUserAPITest {
    private static JSONObject requestBody;
    private static String currentNewUserName;
    private static String currentNewUserID;

    @BeforeClass
    public void setUp() {
        // Any setup needed before running the tests
        currentNewUserName = LogInPage.loginNameData + randomAlphaNumeric(2); // Generate a unique username
        requestBody = new JSONObject()
                .put("userName", currentNewUserName)
                .put("password", LogInPage.loginPasswordData);
    }


    @Test
    public void createNewUserTest() {
        // Implement API call to create a new user
        // Use the loginNameData and loginPasswordData from LogInPage for the request body
        // Assert that the user is created successfully (e.g., check response status code, response body, etc.)
        Response response = given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("https://demoqa.com/Account/v1/User")
                .then()
                .statusCode(201) // Assuming 201 Created is the expected status code for successful
                // extract the userID from the response and store it in CurrentNewUserID for later use
                .extract()
                .response();

        currentNewUserID = response.jsonPath().getString("userID");
        // Print the response and the extracted userID for debugging purposes
        System.out.println("!!! >>> CREATE RESPONSE: " + response.asString());
        System.out.println("!!! >>> EXTRACTED ID: " + currentNewUserID);

    }

    @Test(dependsOnMethods = "createNewUserTest")
    public void UserCreationCheck() {
        // Implement API call to check if the user was created successfully
        // Use the loginNameData from LogInPage to verify the user exists
        // Assert that the user exists (e.g., check response status code, response body, etc.)
        given()
                .pathParam("uuid", currentNewUserID)
                .when()
                .get("https://demoqa.com/Account/v1/User/{uuid}")
                .then()
                .statusCode(200); // Assuming 200 OK is the expected status code for successful retrieval
    }

    @Test (dependsOnMethods = "UserCreationCheck")
    public void deleteUserTest() {
        // Implement API call to delete the user created in the previous test
        // Use the loginNameData from LogInPage to identify the user to be deleted
        // Assert that the user is deleted successfully (e.g., check response status code, response body, etc.)
        given()
                .pathParam("uuid", currentNewUserID)
                .when()
                .delete("https://demoqa.com/Account/v1/User/{uuid}")
                .then()
                .statusCode(204); // Assuming 204 No Content is the expected status code for successful deletion
    }

}
