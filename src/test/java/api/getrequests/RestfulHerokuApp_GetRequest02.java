package api.getrequests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestfulHerokuApp_GetRequest02 {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/321
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "application/json"
    And
        Status Line should be "HTTP/1.1 200 OK"
     */

    @Test
    public void restfulHerokuApp_GetRequest02(){
        //Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/321";

        //Set the expected data
            //There is no data given to the database by API.

        //Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        //Assert the response
        response.
                then().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");
    }
}