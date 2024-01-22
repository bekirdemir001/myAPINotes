package api.putrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import api.testdata.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonPlaceHolder_PutRequest01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/190
        And
            {
                "userId": 21,
                "title": "Clean your dirty room",
                "completed": true
            }
        When
            Send a Put Request to the url
        Then
            Status Code should be 200
        And
            Response Body should be the following:
            {
                 "userId": 21,
                 "title": "Clean your dirty room",
                 "completed": true,
                 "id": 201
            }
     */

    @Test
    public void jsonPlaceHolder_PutRequest01(){
        //Set the URL
        spec.pathParams("first", "todos", "second", "190");

        //Set the expected data
        JsonPlaceHolderTestData jphtd = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jphtd.jsonPlaceHolderTestData(21, "Clean your dirty room", true);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().put("/{first}/{second}");
        //response.prettyPrint();

        //Assert the response
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}