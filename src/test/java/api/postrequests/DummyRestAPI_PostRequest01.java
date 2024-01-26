package api.postrequests;

import api.baseurls.DummyRestApiBaseUrl;
import api.pojos.dummyrestapipojos.DummyRestApiDataPojo;
import api.pojos.dummyrestapipojos.DummyRestApiResponsePojo;
import api.utilities.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DummyRestAPI_PostRequest01 extends DummyRestApiBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create
    And
        {
            "employee_name": "Denzel Washington",
            "employee_salary": 123000,
            "employee_age": 54,
            "profile_image": "Perfect image"
        }
    When
        Send a Post Request to the URL
    Then
        Status Code should be 200
    And
        Response Body should be as the following:
        {
            "status": "success",
            "data": {
                "employee_name": "Denzel Washington",
                "employee_salary": 123000,
                "employee_age": 54,
                "profile_image": "Perfect image",
                "id": 7421
            },
            "message": "Successfully! Record has been added."
        }
     */

    @Test
    public void dummyRestAPI_PutRequest01(){
        //Set the URL
        spec.pathParam("first", "create");

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Denzel Washington", 123000, 54, "Perfect image");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Assert the response
        DummyRestApiResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
    }
}