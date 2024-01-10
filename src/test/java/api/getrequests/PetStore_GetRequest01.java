package api.getrequests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PetStore_GetRequest01 {

    /*
    Given
        https://petstore.swagger.io/v2/pet/123
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
    public void petStoreGetRequest01(){
        //1. Set the URL
        String url = "https://petstore.swagger.io/v2/pet/321";

        //2. Set the expected data
            //There is no data given to the database by API.

        //3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        //4. Assert the response
        response.
                then().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");
    }
}