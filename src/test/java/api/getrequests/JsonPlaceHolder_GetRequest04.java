package api.getrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.pojos.JsonPlaceHolderPojo;
import api.testdata.JsonPlaceHolderTestData;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_GetRequest04 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/123
    When
        Send a Get Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "userId": 7,
            "id": 123,
            "title": "esse et quis iste est earum aut impedit",
            "completed": false
        }
     */

    @Test
    public void jsonPlaceHolder_GetRequest03(){
        //Set the URL
        spec.pathParams("first", "todos", "second", "123");

        //Set the expected data
        String json = JsonPlaceHolderTestData.convertJsonToString(7, "esse et quis iste est earum aut impedit", false);
        Map<String, Object> expectedData = ObjectMapperUtils.convertJsonToJava(json, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        Map<String, Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}