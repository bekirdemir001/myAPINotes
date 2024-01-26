package api.deleterequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_DeleteRequest01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/123
    When
        Send a Delete Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be {}
     */

    @Test
    public void jsonPlaceHolder_DeleteRequest01(){
        //Set the URL
        spec.pathParams("first", "todos", "second", "123");

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();

        //Send the request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData, actualData);
    }
}