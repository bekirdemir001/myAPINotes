package api.getrequests;

import api.baseurls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStore_GetRequest03 extends PetStoreBaseUrl {
    /*
        Given
            https://petstore.swagger.io/v2/pet/1940
        When
            Send a Get Request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response format should be Json
        And
            Name should be "Kitty"
        And
            Status should be "available"
     */

    @Test
    public void petStore_GetRequest03(){
        //Set the URL
        spec.pathParams("first", "pet", "second", 1940);

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
                body("name", equalTo("Kitty"),
                        "status", equalTo("available"));
    }
}