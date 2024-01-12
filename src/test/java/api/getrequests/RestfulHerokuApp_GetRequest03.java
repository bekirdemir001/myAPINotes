package api.getrequests;

import api.baseurls.RestfulHerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestfulHerokuApp_GetRequest03 extends RestfulHerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/589
    When
        Send a Get Request to the url
    Then
        Status Code should be 200
    And
        Content Type should be Json
    And
        Firstname should be "Morgan"
    And
        Lastname should be "Freeman"
    And
        Total Price should be 1940
    And
        Deposit Paid should be false
    And
        Additional Needs should be "Lunch"
     */

    @Test
    public void restfulHerokuApp_GetRequest03(){
        //Set the URL
        spec.pathParams("first", "booking", "second", 589);

        //Set the expected data
            //There is no data given to the database by API.

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //Assert the response
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Morgan"),
                        "lastname", equalTo("Freeman"),
                        "totalprice", equalTo(1940),
                        "depositpaid", equalTo(false),
                        "additionalneeds", equalTo("Lunch"));
    }
}