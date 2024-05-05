import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GetTest {

    private String baseURL = "https://dummyapi.io/data/v1";
    private String validAppId = "6631d707a8ec0d1267edb985";
    private String invalidAppId = "666232334appidinvallid2e23";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseURL;
    }

    @Test(description = "Melakukan get user tanpa header app-id", priority = 1)
    public void getUserWithoutAppIdHeader() {
        Response response = RestAssured.given()
                .get("/user/60d0fe4f5311236168a109cf");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 403);

        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        Assert.assertTrue(responseBody.contains("APP_ID_MISSING"));
    }

    @Test(description = "Melakukan get user dengan header app-id invalid", priority = 2)
    public void getUserWithInvalidAppIdHeader() {
        Response response = RestAssured.given()
                .header("app-id", invalidAppId)
                .get("/user/60d0fe4f5311236168a109cf");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 403);

        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        Assert.assertTrue(responseBody.contains("APP_ID_NOT_EXIST"));
    }

    @Test(description = "Melakukan get user dengan app-id valid dan id yang terdaftar", priority = 3)
    public void getUserWithValidAppIdAndRegisteredId() {
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .get("/user/60d0fe4f5311236168a109cf");

        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        Assert.assertEquals(statusCode, 200);

    }

    @Test(description = "Melakukan get user dengan app-id valid dan id yang tidak terdaftar", priority = 4)
    public void getUserWithValidAppIdAndUnregisteredId() {
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .get("/user/60d0fe4f5311236168a1dddd");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        Assert.assertTrue(responseBody.contains("RESOURCE_NOT_FOUND"));
    }

    @Test(description = "Melakukan get user dengan app-id valid dan format id yang tidak sesuai", priority = 5)
    public void getUserWithValidAppIdAndInvalidFormatId() {
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .get("/user/60d0fe4f53112invalid");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String responseBody = response.getBody().asString();
        TestHelper.printPrettyJson(responseBody, statusCode);

        Assert.assertTrue(responseBody.contains("PARAMS_NOT_VALID"));
    }
}
