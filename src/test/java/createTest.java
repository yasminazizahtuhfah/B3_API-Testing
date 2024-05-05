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

        // Set request headers
        httpRequest.header("app-id", "662715126cae0331efdee794")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\"firstName\":\"Mentari\",\n" +
                "\"lastName\":\"Ayu\",\n" +
                "\"email\":\"mentariiiiiiiiiiiiixxiiaiiiiiiiiiiii@example.com\"}";

        // Add request body
        httpRequest.body(requestBody);

        // Send POST request and get response
        Response response = httpRequest.post();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: \"" + responseBody + "\"");

        // Validate status code
        Assert.assertEquals(statusCode, 200, "User tidak berhasil dibuat");
    }
    @Test
    public void createUser2() {
        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/create";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Set request headers
        httpRequest.header("app-id", "662715126cae0331efdee794")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\"firstName\":\"Mentari\",\n" +
                "\"lastName\":\"Ayu\",\n" +
                "\"email\":\"mentariiiiiiiiiiiaiiiiiiiiiiii@example.com\"}";

        // Add request body
        httpRequest.body(requestBody);

        // Send POST request and get response
        Response response = httpRequest.post();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: \"" + responseBody + "\"");

        // Validate status code
        Assert.assertEquals(statusCode, 200, "User tidak berhasil dibuat");
    }

}
