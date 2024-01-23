package api.postrequests;

import api.baseurls.RestfulHerokuAppBaseUrl;
import api.pojos.restfulpojos.RestfulBookingDatesPojo;
import api.pojos.restfulpojos.RestfulBookingPojo;
import api.pojos.restfulpojos.RestfulResponsePojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestfulHerokuApp_PostRequest02 extends RestfulHerokuAppBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
            "firstname" : "Julia",
            "lastname" : "Roberts",
            "totalprice" : 1940,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2020-02-02",
                "checkout" : "2021-03-03"
            },
            "additionalneeds" : "Lunch"
        }
    When
        Send a Post Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "bookingid": 5056,
            "booking": {
                "firstname": "Julia",
                "lastname": "Roberts",
                "totalprice": 1940,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2023-02-02",
                    "checkout": "2024-03-03"
                },
                "additionalneeds": "Lunch"
            }
        }
     */

    @Test
    public void restfulHerokuApp_PostRequest02(){
        //Set the URL
        spec.pathParam("first", "booking");

        //Set the expected data
        RestfulBookingDatesPojo bookingDates = new RestfulBookingDatesPojo("2020-02-02", "2021-03-03");
        RestfulBookingPojo expectedData = new RestfulBookingPojo("Julia", "Roberts", 1940, false, bookingDates, "Lunch");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Assert the response
        RestfulResponsePojo actualData = response.as(RestfulResponsePojo.class);

        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }
}