import ch.qos.logback.core.net.SyslogOutputStream;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateTest {
    //TC-03-06-Melakukan operasi update pada field title dengan data valid
    @Test
    public void testUpdateUserTitleValid() {
        System.out.println("\nTest: Melakukan operasi update pada field title dengan data valid\n");

        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Set request headers
        httpRequest.header("app-id", "662ba92ffc6498c14d22ae13")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\"title\": \"mrs\"}";

        // Add request body
        httpRequest.body(requestBody);

        // Send PUT request and get response
        Response response = httpRequest.put();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 200, "Status code tidak sesuai dengan yang diharapkan");

        System.out.println("\n==================================================================================================\n");

    }

    //TC-03-19-Melakukan operasi update pada field title dengan data invalid (numerik)
    @Test
    public void testUpdateUserTitleInvalid() {
        System.out.println("\nTest: Melakukan operasi update pada field title dengan data invalid (numerik)\n");

        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Set request headers
        httpRequest.header("app-id", "662ba92ffc6498c14d22ae13")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\"title\": 123456}";

        // Add request body
        httpRequest.body(requestBody);

        // Send PUT request and get response
        Response response = httpRequest.put();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 400, "Status code tidak sesuai dengan yang diharapkan");

        System.out.println("\n==================================================================================================\n");
    }

    //TC-03-46-Melakukan operasi update pada field country dengan data invalid (length < 2)
    @Test
    public void testUpdateUserCountryInvalid() {
        System.out.println("\nTest: Melakukan operasi update pada field country dengan data invalid (length < 2)\n");

        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Set request headers
        httpRequest.header("app-id", "662ba92ffc6498c14d22ae13")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\n" +
                "  \"location\": {\n" +
                "    \"country\": \"j\"\n" +
                "  }\n" +
                "}";

        // Add request body
        httpRequest.body(requestBody);

        // Send PUT request and get response
        Response response = httpRequest.put();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 400, "Status code tidak sesuai dengan yang diharapkan");

        System.out.println("\n==================================================================================================\n");
    }

    //TC-03-48-Melakukan operasi update pada field gender dengan data invalid (selain dari opsi "male", "female", "other", "")
    @Test
    public void testUpdateUserGenderInvalid() {
        System.out.println("\nTest: Melakukan operasi update pada field gender dengan data invalid (selain dari opsi male, female, other, string kosong\n");

        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Set request headers
        httpRequest.header("app-id", "662ba92ffc6498c14d22ae13")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\"gender\": \"unknown\"}";

        // Add request body
        httpRequest.body(requestBody);

        // Send PUT request and get response
        Response response = httpRequest.put();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 400, "Status code tidak sesuai dengan yang diharapkan");

        System.out.println("\n==================================================================================================\n");
    }

    //TC-03-50-Melakukan operasi update pada field email dengan data valid
    @Test
    public void testUpdateUserEmailInvalid() {
        System.out.println("\nTest: Melakukan operasi update pada field email dengan data valid\n");

        // Specify base URI
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca";

        // Request object (whenever you want to send request to the server)
        RequestSpecification httpRequest = RestAssured.given();

        // Set request headers
        httpRequest.header("app-id", "662ba92ffc6498c14d22ae13")
                .contentType("application/json");

        // Set request body
        String requestBody = "{\"email\": \"yasmin@gmail.com\"}";

        // Add request body
        httpRequest.body(requestBody);

        // Send PUT request and get response
        Response response = httpRequest.put();

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 400, "Status code tidak sesuai dengan yang diharapkan");

        System.out.println("\n==================================================================================================\n");
    }
}



