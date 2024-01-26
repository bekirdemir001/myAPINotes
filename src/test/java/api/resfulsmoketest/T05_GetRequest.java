package api.resfulsmoketest;

import api.baseurls.RestfulHerokuAppBaseUrl;
import api.pojos.restfulpojos.RestfulBookingDatesPojo;
import api.pojos.restfulpojos.RestfulBookingPojo;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class T05_GetRequest extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/3895
    When
        Send a Get Request to the URL
    Then
        Status Code should be 404
    And
        Response Body should be "Not Found"
     */

    @Test
    public void findBooking(){
        //Set the URL
        spec.pathParams("first", "booking", "second", 1587);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //Assert the response
        String actualData = response.asString();
        System.out.println("actualData = " + actualData);

        assertEquals(404, response.statusCode());
        assertEquals(expectedData, actualData);
    }
}