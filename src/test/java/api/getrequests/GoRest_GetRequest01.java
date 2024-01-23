package api.getrequests;

import api.baseurls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class GoRest_GetRequest01 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        Send a Get Request to the URL
    And
         The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should be 10
    And
        We have at least one "active" status
    And
        "Deeksha Somayaji", "Ms. Satyen Gupta", and "Chandranath Reddy" are among the users
    And
        The female users are less than or equals to male users
     */

    @Test
    public void goRest_GetRequest01(){
        //Set the URL
        spec.pathParam("first", "users");

        //Set the expected data
            //There is no data give to the URL by using API.

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        //Assert the response
            //1.Way:
        response.then().body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id", hasSize(10),
                "data.status", hasItem("active"),
                "data.name", hasItems("Deeksha Somayaji", "Ms. Satyen Gupta", "Chandranath Reddy"));

        JsonPath jsonPath = response.jsonPath();
        List<String> genders = jsonPath.getList("data.gender");
        System.out.println("Genders: " + genders);

            //1.Way: For-Each-Loop
        int females = 0;
        for (String w : genders){
            if (w.equals("female")){
                females++;
            }
        }
        System.out.println("The number of females with For-Each-Loop: " + females);
        assertTrue(females <= (genders.size()-females));

            //2.Way: Lambda
        int females2 = (int)genders.stream().filter(t->t.equals("female")).count();
        System.out.println("The number of females with Lambda: " + females2);
        assertTrue(females2 <= (genders.size()-females2));

            //3.Way: Groovy Language
        List<String> femaleList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("Female List: " + femaleList);

        List<String> maleList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("Male List: " + maleList);

        assertTrue(femaleList.size() <= maleList.size());
    }
}