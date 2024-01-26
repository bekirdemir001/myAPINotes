package api.resfulsmoketest;

import api.baseurls.RestfulHerokuAppBaseUrl;
import api.pojos.restfulpojos.RestfulBookingDatesPojo;
import api.pojos.restfulpojos.RestfulBookingPojo;
import api.pojos.restfulpojos.RestfulResponsePojo;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;


import static api.utilities.RestfulToken.generateToken;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class T03_PutRequest extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/3895
    And
        {
            "firstname" : "Antony",
            "lastname" : "Hopkins",
            "totalprice" : 123,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Dinner"
        }
    When
        Send a Put Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "firstname": "Antony",
            "lastname": "Hopkins",
            "totalprice": 123,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Dinner"
        }
     */

    @Test
    public void updateBooking(){
        //Set the URL
        spec.pathParams("first", "booking", "second", 4106);

        //Set the expected data
        RestfulBookingDatesPojo bookingDates = new RestfulBookingDatesPojo("2018-01-01", "2019-01-01");
        RestfulBookingPojo expectedData = new RestfulBookingPojo("Antony", "Hopkins", 123, true, bookingDates, "Dinner");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).header("Cookie", "token=" + generateToken()).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        RestfulBookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), RestfulBookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
    }
}
