package api.postrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.testdata.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_PostRequest02 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    And
        {
            "userId": 55,
            "title": "Do your homework",
            "completed": true
        }
    When
        Send a Post Request to the URL
    Then
        Status Code should be 201
    And
        Content Type should be JSON
    And
        Response body should be the following:
        {
            "userId": 55,
            "title": "Do your homework",
            "completed": true,
            "id": 201
        }
     */

    @Test
    public void jsonPlaceHolder_PostRequest02(){
        //Set the URL
        spec.pathParam("first", "todos");

        //Set the expected data
        JsonPlaceHolderTestData jphtd = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jphtd.jsonPlaceHolderTestData(55, "Do your homework", true);

        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Assert the response
        Map<String, Object> actualDate = response.as(HashMap.class);

        System.out.println("Actual Data: " + actualDate);

        assertEquals(201, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

        assertEquals(expectedData.get("userId"), actualDate.get("userId"));
        assertEquals(expectedData.get("title"), actualDate.get("title"));
        assertEquals(expectedData.get("completed"), actualDate.get("completed"));
    }
}