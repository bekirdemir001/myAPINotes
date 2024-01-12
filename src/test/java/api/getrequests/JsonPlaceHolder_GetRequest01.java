package api.getrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JsonPlaceHolder_GetRequest01 extends JsonPlaceHolderBaseUrl{
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/200
    When
        Send a Get request to the URL
    Then
        Status Code should be 200
    And
        Content Type should be Json
    And
        User ID should be 10
    And
        ID should be 200
    And
        Title should be "ipsam aperiam voluptates qui"
    And
        Completed should be false
     */

    @Test
    public void jsonPlaceHolder_GetRequest01(){
        //Set the URL
        spec.pathParams("first" , "todos", "second", 200);

        //Set the expected data
            //There is no data given to the database by API.

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //Assert the response
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId",equalTo(10),
                        "id",equalTo(200),
                        "title",equalTo("ipsam aperiam voluptates qui"),
                        "completed",equalTo(false));
    }
}