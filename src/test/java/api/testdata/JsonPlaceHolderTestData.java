package api.testdata;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    /*
    Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55.0);
        expectedData.put("title", "Do your homework");
        expectedData.put("completed", true);
     */

    public Map<String, Object> jsonPlaceHolderTestData(Integer userId, String title, Boolean completed){
        Map<String, Object> expectedData = new HashMap<>();
        if (userId != null) {
            expectedData.put("userId", userId);
        }
        if(title != null) {
            expectedData.put("title", title);
        }
        if (completed != null) {
            expectedData.put("completed", completed);
        }
        return expectedData;
    }
}