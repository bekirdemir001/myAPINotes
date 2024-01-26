package api.testdata;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
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

    public static String convertJsonToString(Integer userId, String title, Boolean completed){
        return "{ \"userId\": "+userId+", \"title\": \""+title+"\", \"completed\": "+completed+" }";
    }
}