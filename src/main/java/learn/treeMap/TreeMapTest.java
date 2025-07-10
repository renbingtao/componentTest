package learn.treeMap;

import java.util.TreeMap;

public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, Object> map = new TreeMap<>();
        map.put(4, "4");
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        //1:1
        //2:2
        //3:3
        //4:4
        //TreeMap是升序排序的，按key排序
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
