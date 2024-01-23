package api.postrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.pojos.JsonPlaceHolderPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_PostRequest03 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    And
        {
            "userId": 40,
            "title": "Learn API Automation",
            "completed": true
        }
    When
        Send a Post Request to the URL(by using POJO Class)
    Then
        Status Code should be 201
    And
        Response Body should be as the following:
        {
            "userId": 40,
            "title": "Learn API Automation",
            "completed": true,
            "id": 201
        }
     */

    @Test
    public void jsonPlaceHolder_PostRequest03(){
        //Set the URL
        spec.pathParam("first", "todos");

        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(40, "Learn API Automation", true);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Assert the response
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("Actual Data: " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }
}