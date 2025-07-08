package test.test.json;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GsonTest {

    @Test
    public void test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ab", "gds");
        map.put("de", 444);

        Gson gson = new Gson();
        String json = gson.toJson(map);

        System.out.println(json);
    }

    @Test
    public void test2() {
        Gson gson = new Gson();
        ArrayList<Integer> folderIdList = new ArrayList<>();
        folderIdList.add(2);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token", "abc");
        map.put("folderIdList", folderIdList);
        map.put("async", false);
        String json = gson.toJson(map);
        System.out.println(json);
    }

    @Test
    public void test3() {
        String str = "{\"async\":false,\"folderIdList\":\"hello\",\"token\":\"abc\"}";
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);
        map.forEach((k, v) -> {
            System.out.println("k=" + k + ",v=" + v);
        });
    }

}
