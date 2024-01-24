package api.getrequests;

import api.baseurls.RestfulHerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestfulHerokuApp_GetRequest04 extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/303
    When
        Send a Get Request to the URL
    Then
        Status Code should be 200
    And
        Content Type should be JSON
    And
        Response Body should be the following:
        {
            "firstname" : "Tom",
            "lastname" : "Cruise",
            "totalprice" : 1955,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2020-01-01",
                "checkout" : "2021-03-03"
            },
            "additionalneeds" : "Midnight Snack"
        }
     */

    @Test
    public void restfulHerokuApp_GetRequest04(){
        //Set the URL
        spec.pathParams("first", "booking", "second","303");

        //Set the expected data
            //There is no data given to the database by API

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
            //1.Way:
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Tom"),
                        "lastname", equalTo("Cruise"),
                        "totalprice", equalTo(1955),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2020-01-01"),
                        "bookingdates.checkout", equalTo("2021-03-03"),
                        "additionalneeds", equalTo("Midnight Snack"));

            //2.Way:
        JsonPath jsonPath = response.jsonPath();

        assertEquals("Tom", jsonPath.getString("firstname"));
        assertEquals("Cruise", jsonPath.getString("lastname"));
        assertEquals(1955, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2020-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2021-03-03", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Midnight Snack", jsonPath.getString("additionalneeds"));
    }
}