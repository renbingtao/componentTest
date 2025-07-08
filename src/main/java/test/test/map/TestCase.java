package test.test.map;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestCase {

    @Test
    public void test() {
        HashMap<Long, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put((long) 45674596, new Object());
        System.out.println(objectObjectHashMap.get((long) 45674596));
    }

    @Test
    public void test2() {
        String[][] array = {
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        };
        Map<String, String> map = toMap(array);
        map.forEach((k, v) -> {
            System.out.println(k + "||" + v);
        });

        Map<Object, Object> map2 = ArrayUtils.toMap(array);
        map2.forEach((k, v) -> {
            System.out.println(k + "||" + v);
        });
    }

    @Test
    public void test3() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        System.out.println(map.toString());
    }

    @Test
    public void test4() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        System.out.println(map.toString());
    }

    private static Map<String, String> toMap(String[][] array) {
        return IntStream.range(0, array.length).boxed().collect(Collectors.toMap(i -> array[i][0], i -> array[i][1]));
    }

}
