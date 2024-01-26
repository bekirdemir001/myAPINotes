package api.resfulsmoketest;

import api.baseurls.RestfulHerokuAppBaseUrl;
import api.pojos.restfulpojos.RestfulBookingDatesPojo;
import api.pojos.restfulpojos.RestfulBookingPojo;
import api.pojos.restfulpojos.RestfulResponsePojo;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class T02_GetRequest extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/3895
    When
        Send a Get Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "firstname": "Julia",
            "lastname": "Roberts",
            "totalprice": 1940,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2020-02-02",
                "checkout": "2021-03-03"
            },
            "additionalneeds": "Lunch"
        }
     */

    @Test
    public void findBooking(){
        //Set the URL
        spec.pathParams("first", "booking", "second", 4106);

        //Set the expected data
        RestfulBookingDatesPojo bookingDates = new RestfulBookingDatesPojo("2020-02-02", "2021-03-03");
        RestfulBookingPojo expectedData = new RestfulBookingPojo("Julia", "Roberts", 1940, false, bookingDates, "Lunch");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
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