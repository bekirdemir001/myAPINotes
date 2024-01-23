package api.postrequests;

import api.baseurls.RestfulHerokuAppBaseUrl;
import api.testdata.RestfulHeroukuAppTestData;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestfulHerokuApp_PostRequest01 extends RestfulHerokuAppBaseUrl{
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
            "firstname" : "Morgan",
            "lastname" : "Freeman",
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
            "bookingid": 4009,
            "booking": {
                "firstname": "Morgan",
                "lastname": "Freeman",
                "totalprice": 1940,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2023-02-02",
                    "checkout": "2024-03-03"
                },
                "additionalneeds": "Dinner"
            }
        }
     */

    @Test
    public void restfulHerokuApp_PostRequest01(){
        //Set the URL
        spec.pathParam("first", "booking");

        //Set the expected data
        RestfulHeroukuAppTestData restful = new RestfulHeroukuAppTestData();
        Map<String, Object> expectedData = restful.outherMap("Morgan", "Freeman", 1940,
                false, "2023-02-02", "2024-03-03", "Dinner");

        System.out.println("Expected Data: " + expectedData);

        //Send the request get the response
        Response response = given().spec(spec).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Assert the response
        Map<String, Object> actualData = response.as(HashMap.class);

        System.out.println("Actual Data: " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));

        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) ((Map) actualData.get("booking")).
                get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"), ((Map) ((Map) actualData.get("booking")).
                get("bookingdates")).get("checkout"));

        assertEquals(expectedData.get("additionalneeds"), ((Map) actualData.get("booking")).get("additionalneeds"));
    }
}