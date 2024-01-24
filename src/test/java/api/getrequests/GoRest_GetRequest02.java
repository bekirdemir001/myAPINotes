package api.getrequests;

import api.baseurls.GoRestBaseUrl;
import api.pojos.gorestpojos.GoRestDataPojo;
import api.pojos.gorestpojos.GoRestPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GoRest_GetRequest02 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users/1188531
    When
        Send a Get Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "meta": null,
            "data": {
                "id": 6027270,
                "name": "Dandak Mehrotra Jr.",
                "email": "jr_mehrotra_dandak@mueller.example",
                "gender": "female",
                "status": "active"
            }
        }
     */

    @Test
    public void goRest_GetRequest02(){
        //Set the URL
        spec.pathParams("first", "users", "second", "6027270");

        //Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(6027270, "Dandak Mehrotra Jr.", "jr_mehrotra_dandak@mueller.example", "female", "active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojo);

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(goRestDataPojo.getId(), actualData.getData().getId());
        assertEquals(goRestDataPojo.getName(), actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(), actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());
    }
}