import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateTest {

    private String baseUrl = "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca";
    private String validAppId = "662ba92ffc6498c14d22ae13";

    @BeforeClass
    public void setUp() {
        // Set base URL
        RestAssured.baseURI = baseUrl;
    }

    //TC-03-06-Melakukan operasi update pada field title dengan data valid
    @Test
    public void testUpdateUserTitleValid() {
        // Request body
        String requestBody = "{\"title\": \"mrs\"}";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", validAppId)
                .body(requestBody)
                .when()
                .put()
                .then()
                .statusCode(200)
                .body("title", equalTo("mrs"))
                .log().body()
                .log().status();
    }

    @Test
    public void testUpdateUserTitleInvalid() {
        // Request body with invalid data (numerical value for title)
        String requestBody = "{\"title\": 123456}";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", validAppId)
                .body(requestBody)
                .when()
                .put()
                .then()
                .statusCode(400) // Expected status code
                .contentType(ContentType.JSON)
                .body("error", equalTo("BODY_NOT_VALID")) // Expected error message
                .log().body()
                .log().status();
    }
}
