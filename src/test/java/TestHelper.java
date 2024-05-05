import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class TestHelper {

    public static void printPrettyJson(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Object json = objectMapper.readValue(responseBody, Object.class);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            System.out.println("Response Body: \n" + prettyJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
