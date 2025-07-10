package learn.concurrentSkipListMap;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {
        //线程安全且有序的map，基于跳跃表实现
        ConcurrentSkipListMap<Object, Object> map = new ConcurrentSkipListMap<>();
        map.put(4, "4");
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        //和TreeMap一样，是升序排序的，按key排序
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
