package api.testdata;

import java.util.HashMap;
import java.util.Map;

public class RestfulHeroukuAppTestData {
    public Map<String, Object> outherMap(String firstname, String lastname, Integer totalprice, Boolean depositpaid,
                                         String checkin, String checkout, String additionalneeds){

        Map<String, Object> outherMap = new HashMap<>();
        outherMap.put("firstname", firstname);
        outherMap.put("lastname", lastname);
        outherMap.put("totalprice", totalprice);
        outherMap.put("depositpaid", depositpaid);
        outherMap.put("bookingdates", innerMap(checkin, checkout));
        outherMap.put("additionalneeds", additionalneeds);

        return outherMap;
    }

    public Map<String, String> innerMap(String checkIn, String checkOut){
        Map<String, String> innerMap = new HashMap<>();
        innerMap.put("checkin", checkIn);
        innerMap.put("checkout", checkOut);
        return innerMap;
    }
}