package api.postrequests;

import api.baseurls.RestfulHerokuAppBaseUrl;
import api.testdata.RestfulHeroukuAppTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestfullHerokuApp_PostRequest03 extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
            "firstname" : "Morgan",
            "lastname" : "Freeman",
            "totalprice" : 1940,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2020-02-02",
                "checkout" : "2021-03-03"
            },
            "additionalneeds" : "Midnight Snack"
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
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-02-02",
                    "checkout": "2024-03-03"
                },
                "additionalneeds": "Midnight Snack"
            }
        }
     */

    @Test
    public void restfullHerokuApp_PostRequest03() throws JsonProcessingException {
        //Set the URL
        spec.pathParam("first", "booking");

        //Set the expected data
        RestfulHeroukuAppTestData rhatd = new RestfulHeroukuAppTestData();
        Map<String, Object> expectedData = rhatd.outherMap("Morgan", "Freeman", 1940, true, "2020-02-02", "2021-03-03", "Midnight Snack");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Assert the response
        Map<String, Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), ((Map) actualData.get("booking")).get("additionalneeds"));
    }
}