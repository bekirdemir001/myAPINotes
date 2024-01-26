package api.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestfulToken {
    public static String generateToken(){
        String url = "https://restful-booker.herokuapp.com/auth";
        Map<String, String> credential = new HashMap<>();
        credential.put("username", "admin");
        credential.put("password", "password123");
        Response response = given().contentType(ContentType.JSON).body(credential).when().post(url);
        return response.jsonPath().getString("token");
    }
}
