package api.testdata;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
    public Map<String, Object> outherMap(Object meta, String name, String email, String gender, String status){
        Map<String, Object> outherMap = new HashMap<>();
        outherMap.put("meta", meta);
        outherMap.put("data", innerMap(name, email, gender, status));
        return outherMap;
    }

    public Map<String, String> innerMap(String name, String email, String gender, String status){
        //There is no need to add ids into innerMap.
        Map<String, String> innerMap = new HashMap<>();
        innerMap.put("name", name);
        innerMap.put("email", email);
        innerMap.put("gender", gender);
        innerMap.put("status", status);
        return innerMap;
    }
}