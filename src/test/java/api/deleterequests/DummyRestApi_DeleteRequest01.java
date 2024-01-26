package api.deleterequests;

import api.baseurls.DummyRestApiBaseUrl;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DummyRestApi_DeleteRequest01 extends DummyRestApiBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/delete/2
    When
        Send a Delete Request to the URL
    Then
        Status Code should be 200
    And
        Status should be ""success"
    And
        Data should be "2"
    And
        Message should be "Successfully! Record has been deleted"
     */

    @Test
    public void DummyRestApi_DeleteRequest01(){
        //Set the URL
        spec.pathParams("first", "delete", "second", 2);

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("status", "success");
        expectedData.put("data", "2");
        expectedData.put("message", "Successfully! Record has been deleted");

        //Send the request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Assert the response
        Map<String, String> actualData  = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("status"), actualData.get("status"));
        assertEquals(expectedData.get("data"), actualData.get("data"));
        assertEquals(expectedData.get("message"), actualData.get("message"));
    }
}