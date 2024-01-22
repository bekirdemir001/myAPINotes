package api.getrequests;

import api.baseurls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonPlaceHolder_GetRequest02 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        Send a Get Request to the URL
    Then
        Status Code should be 200
    And
        Print all ids greater than 190 on the console
        Assert that there are 10 ids greater than 190
    And
        Print all userIds whose ids are less than 5 on the console
        Assert that the number of userIds whose ids are less than 5 is 4
    And
        Print all titles whose ids are less than 5
        Assert that "delectus aut autem" is one of the titles whose id is less than 5


    Note: "findAll{it.id>190}.id" This code is a Groovy Language based on Java.  It is used for sorting.
     */

    @Test
    public void jsonPlaceHolder_GetRequest02(){
        //Set the URL
        spec.pathParam("first", "todos");

        //Set the expected data
            //There is no data given to the database by API.

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        //Assert the response
        assertEquals(200, response.statusCode());

            //Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idList = jsonPath.getList("findAll{it.id>190}.id");
            //Assert that there are 10 ids greater than 190
        System.out.println("Id List whose ids are greater than 190: " + idList);
        assertEquals(10, idList.size());

            //Print all userIds whose ids are less than 5 on the console
        List<Integer> idList2 = jsonPath.getList("findAll{it.id<5}.id");
        System.out.println("Id List whose ids are less than 5: " + idList2);
            //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4, idList2.size());

            //Print all titles whose ids are less than 5
        List<String> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("Title List whose ids are less than 5: " + titleList);
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titleList.contains("delectus aut autem"));
    }
}