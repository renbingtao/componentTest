package test.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionTest {

    @Test
    public void test1() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(-1);

        List<Integer> collect = objects.stream().filter(e -> {
            return e > 0;
        }).map(e -> {
            return e + 1;
        }).collect(Collectors.toList());
        for (Integer i : collect) {
            System.out.println(i);
        }
    }

    @Test
    public void test2() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(-1);

        Set<Integer> collect2 = objects.stream().filter(e -> {
            return e > 0;
        }).map(e -> {
            return e + 1;
        }).collect(Collectors.toSet());
        for (Integer i : collect2) {
            System.out.println(i);
        }
    }

    @Test
    public void test3() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(-1);

        String response = objects.stream().filter(e -> {
            return e > 0;
        }).map(e -> {
            return e + "1";
        }).collect(Collectors.joining(","));

        System.out.println(response);

    }

    @Test
    public void test4(){
        String str = "";
        int i = Integer.parseInt(str);
        System.out.println(i);
    }

}
