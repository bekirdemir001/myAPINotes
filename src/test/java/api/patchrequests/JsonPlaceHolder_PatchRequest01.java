package api.patchrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.testdata.JsonPlaceHolderTestData;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_PatchRequest01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/190
    And
        {
            "title": "Study hard to achieve"
        }
    When
        Send a Patch Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "userId": 10,
            "id": 190,
            "title": "Study hard to achieve",
            "completed": true
        }
     */

    @Test
    public void jsonPlaceHolder_PatchRequest01(){
        //Set the URL
        spec.pathParams("first", "todos", "second", "190");

        //Set the expected data
        JsonPlaceHolderTestData jphtd = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jphtd.jsonPlaceHolderTestData(null, "Study hard to achieve", null);

        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(10, actualData.get("userId"));
        assertEquals(190, actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(true, actualData.get("completed"));
    }
}