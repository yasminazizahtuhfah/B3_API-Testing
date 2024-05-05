import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class createTest {

    @Test
    public void createUser() {
        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/create";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Add app-id header
        httpRequest.header("app-id", "662715126cae0331efdee794")
                // Request payload sending along with post request
                .body("{\"firstName\":\"Mentari\",\n" +
                        "\"lastName\":\"Ayu\",\n" +
                        "\"email\":\"mentariiiiiiiiiiiiiiii@example.com\"}")
                // Set content type as JSON
                .contentType("application/json");

        // Response object
        Response response = httpRequest.post();

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Get status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 200, "Failed to create user. Status code is not as expected.");
    }
}
