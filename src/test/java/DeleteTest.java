import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteTest {

    private String baseURL = "https://dummyapi.io/data/v1";
    private String validAppId = "6631d707a8ec0d1267edb985";
    private String invalidAppId = "666232334appidinvallid2e23";
    private String userIdToDelete = "60d0fe4f5311236168a109d4"; // Id user untuk dihapus

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseURL;
    }

    @Test(description = "Melakukan delete user tanpa header app-id", priority = 1)
    public void deleteUserWithoutAppIdHeader() {
        Response response = RestAssured.given()
                .delete("/user/" + userIdToDelete);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 403);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("APP_ID_MISSING"));
    }

    @Test(description = "Melakukan delete user dengan header app-id invalid", priority = 2)
    public void deleteUserWithInvalidAppIdHeader() {
        Response response = RestAssured.given()
                .header("app-id", invalidAppId)
                .delete("/user/" + userIdToDelete);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 403);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("APP_ID_NOT_EXIST"));
    }

    @Test(description = "Melakukan delete user dengan app-id valid dan id yang terdaftar", priority = 3)
    public void deleteUserWithValidAppIdAndRegisteredId() {
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .delete("/user/" + userIdToDelete);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        // Validasi body response sesuai kebutuhan
        // Misalnya, memeriksa bahwa user dengan id yang sesuai telah dihapus
    }

    @Test(description = "Melakukan delete user dengan app-id valid dan id untuk user yang sudah dihapus sebelumnya", priority = 4)
    public void deleteUserWithValidAppIdAndDeletedId() {
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .delete("/user/" + userIdToDelete);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("RESOURCE_NOT_FOUND"));
    }

    @Test(description = "Melakukan delete user dengan app-id valid dan id yang tidak terdaftar", priority = 5)
    public void deleteUserWithValidAppIdAndUnregisteredId() {
        String unregisteredUserId = "60d0fe4f5311236168a1dddd";
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .delete("/user/" + unregisteredUserId);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("RESOURCE_NOT_FOUND"));
    }

    @Test(description = "Melakukan delete user dengan app-id valid dan format id yang tidak sesuai", priority = 6)
    public void deleteUserWithValidAppIdAndInvalidFormatId() {
        Response response = RestAssured.given()
                .header("app-id", validAppId)
                .delete("/user/60d0fe4f53112invalid");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("PARAMS_NOT_VALID"));
    }
}
