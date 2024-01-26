package api.getrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.pojos.JsonPlaceHolderPojo;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_GetRequest03 extends JsonPlaceHolderBaseUrl {
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
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(7, "esse et quis iste est earum aut impedit", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }
}