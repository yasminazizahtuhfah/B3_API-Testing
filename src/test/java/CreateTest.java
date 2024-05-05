import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateTest {
    private static final String BASE_URL = "https://dummyapi.io/data/v1/user/create";
    private static final String APP_ID = "662715126cae0331efdee794";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    //TC1
    //TC-002-08-Melakukan penambahan data user (Create) dengan Seluruh field diisikan data sesuai dengan range dan ketentuan
    @Test(description = "Create user with all fields filled", priority = 6)
    public void createUserLengkap() {
        String requestBody = "{" +
                "\"title\": \"ms\"," +
                "\"firstName\": \"Mentari\"," +
                "\"lastName\": \"Ayu\"," +
                "\"gender\": \"female\"," +
                "\"email\": \"mentari.ayuuu11@example.com\"," +
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

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("app-id", APP_ID)
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response response = httpRequest.post();

        System.out.println("Test: createUserLengkap");
        int statusCode = response.getStatusCode();
        TestHelper.printPrettyJson(response.getBody().asString(), statusCode);
        System.out.println("=============================================================================\n");


        Assert.assertEquals(statusCode, 200);
    }

    //TC2
    //TC-002-13-Melakukan penambahan data user (Create) dengan Semua field diisi data yang valid dan sesuai range, namun tanpa mengisikan firstName, lastName, ataupun email
    @Test(description = "Create user without mandatory fields", priority = 7)
    public void createTanpaMandatory() {
        String requestBody = "{" +
                "\"title\": \"ms\"," +
                "\"firstName\": \"\"," +
                "\"lastName\": \"\"," +
                "\"gender\": \"female\"," +
                "\"email\": \"\"," +
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

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("app-id", APP_ID)
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response response = httpRequest.post();

        System.out.println("Test: createTanpaMandatory");
        int statusCode = response.getStatusCode();
        TestHelper.printPrettyJson(response.getBody().asString(), statusCode);
        System.out.println("=============================================================================\n");

        Assert.assertEquals(statusCode, 400);
    }

    //TC3
    //TC-02-27-Melakukan penambahan data user (Create) dengan semua field diisi data yang valid dan sesuai range, namun format penulisan dateOfBrith tidak sesuai dengan ISO Date (menggunakan format MM-DD-YYYY)
    @Test(description = "Create user with incorrect date format", priority = 8)
    public void createSalahFormat_dateOfBirth() {
        String requestBody = "{" +
                "\"title\": \"ms\"," +
                "\"firstName\": \"Mentari\"," +
                "\"lastName\": \"Ayu\"," +
                "\"gender\": \"female\"," +
                "\"email\": \"mentari12@example.com\"," +
                "\"dateOfBirth\": \"04-26-2001\"," +  // Date format changed for demonstration
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

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("app-id", APP_ID)
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response response = httpRequest.post();

        System.out.println("Test: createSalahFormat_dateOfBirth");
        int statusCode = response.getStatusCode();
        TestHelper.printPrettyJson(response.getBody().asString(), statusCode);
        System.out.println("=============================================================================\n");

        Assert.assertEquals(statusCode, 400);
    }

    //TC4
    //TC-02-16-Melakukan penambahan data user (Create) dengan Semua field diisi data yang valid dan sesuai range, namun field lastName diisi format integer
    @Test(description = "Create user with incorrect last name format", priority = 9)
    public void createSalahFormat_lastName() {
        String requestBody = "{" +
                "\"title\": \"ms\"," +
                "\"firstName\": \"Mentari\"," +
                "\"lastName\": 471," + // Last name in incorrect format
                "\"gender\": \"female\"," +
                "\"email\": \"me.ayu@example.com\"," +
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

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("app-id", APP_ID)
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response response = httpRequest.post();

        System.out.println("Test: createSalahFormat_lastName");
        int statusCode = response.getStatusCode();
        TestHelper.printPrettyJson(response.getBody().asString(), statusCode);
        System.out.println("=============================================================================\n");

        Assert.assertEquals(statusCode, 400);
    }

    //TC5
    //TC-02-46-Melakukan penambahan data user (Create) dengan semua field diisi dengan input data yang tidak sesuai
    @Test(description = "Create user with invalid input", priority = 10)
    public void createInvalid() {
        String requestBody = "{" +
                "\"title\": \"sus\"," +
                "\"firstName\": \"MentariMentariMentariMentariMentariMentariMentariMentariMentari\"," +
                "\"lastName\": \"AyuAlysiaAyuAlysiaAyuAlysiaAyuAlysiaAyuAlysiaAyuAlysia\"," +
                "\"gender\": \"banteng\"," +
                "\"email\": \"mentariexample.com\"," +
                "\"dateOfBirth\": \"20010426\"," +
                "\"phone\": \"0982xxxxxxxxxxxxxx18240182\"," +
                "\"picture\": \"https://example.com/picture\"," +
                "\"location\": {" +
                "\"street\": \"Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Cari Ca\"," +
                "\"city\": \"Bandung Bandung Bandung Bandung Bandung \"," +
                "\"state\": \"West Java West Java West Java West Java West Java \"," +
                "\"country\": \"Indonesia Indonesia Indonesia Indonesia \"," +
                "\"timezone\": \"+70:00\"" +
                "}" +
                "}";

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("app-id", APP_ID)
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response response = httpRequest.post();

        System.out.println("Test: createInvalid");
        int statusCode = response.getStatusCode();
        TestHelper.printPrettyJson(response.getBody().asString(), statusCode);
        System.out.println("=============================================================================\n");

        Assert.assertEquals(statusCode, 400, "Expected HTTP status code 400 for invalid input");
    }

}
