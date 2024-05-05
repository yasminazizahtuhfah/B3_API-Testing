import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateTest {

    private String baseURL = "https://dummyapi.io/data/v1/user/create";
    private String appId = "662715126cae0331efdee794";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseURL;
    }

    //TC1
    //TC-02-08
    @Test(description = "Melakukan penambahan data user (Create) dengan Seluruh field diisikan data sesuai dengan range dan ketentuan", priority = 1)
    public void createUserLengkap() {
        System.out.println("\nTest: Melakukan penambahan data user (Create) dengan Seluruh field diisikan data sesuai dengan range dan ketentuan\n");
        String requestBody = "{\"title\": \"ms\"," +
                "\"firstName\": \"Mentari\"," +
                "\"lastName\": \"Ayu\"," +
                "\"gender\": \"female\"," +
                "\"email\": \"mentariiayuu@example.com\"," +
                "\"dateOfBirth\": \"2001-04-26\"," +
                "\"phone\": \"098218240182\"," +
                "\"picture\": \"https://example.com/picture.jpg\"," +
                "\"location\": {" +
                "\"street\": \"321 Carik\"," +
                "\"city\": \"Bandung\"," +
                "\"state\": \"West Java\"," +
                "\"country\": \"ID\"," +
                "\"timezone\": \"+7:00\"" +
                "}" +
                "}";
        Response response = RestAssured.given()
                .header("app-id", appId)
                .body(requestBody)
                .post(baseURL);

        // Get status code
        int statusCode = response.getStatusCode();

        // Print response body
        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        // Validate status code
        Assert.assertEquals(statusCode, 200, "Pembuatan user untuk TC-08 gagal\n");

        System.out.println("\n==================================================================================================\n");
    }
}
