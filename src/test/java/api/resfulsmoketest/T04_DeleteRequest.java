package api.resfulsmoketest;

import api.baseurls.RestfulHerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static api.utilities.RestfulToken.generateToken;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class T04_DeleteRequest extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/3895
    When
        Send a Delete Request to the URL
    Then
        Status Code should be 201
    And
        Response Body should be "Created"
     */

    @Test
    public void deleteBooking(){
        //Set the URL
        spec.pathParams("first", "booking", "second", 4106);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given().spec(spec).header("Cookie", "token=" + generateToken()).when().delete("/{first}/{second}");
        //response.prettyPrint();

        //Assert the response
        String actualData = response.asString();
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData, actualData);
    }
}