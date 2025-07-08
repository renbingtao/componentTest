package test.test.array;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {

    @Test
    public void test() {
        ArrayList<String> folderPath = new ArrayList<>();
        folderPath.add("abc");
        folderPath.add("def");
        String[] array = folderPath.toArray(new String[folderPath.size()]);
        System.out.println(Arrays.toString(array));
        String join = String.join(",", array);
        System.out.println(join);
    }

    @Test
    public void test2() {
        String abc = "a,b,c,d";
        String[] split = abc.split(",");
        List<String> folderList = Arrays.asList(split);
        ArrayList<String> list = new ArrayList<>(folderList);

        String folderName = "e";
        list.add(folderName);
        System.out.println(list.toString());
    }

    @Test
    public void test3() {
        String[] aa = new String[]{"a", "b"};
        System.out.println(Arrays.toString(aa));
    }

    @Test
    public void test4() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ygui");
        String[] arr = list.toArray(new String[0]);
        for (String arrStr : arr) {
            System.out.println(arrStr);
        }
    }

    @Test
    public void test5() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ygui");

        String[] fileIdArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            fileIdArr[i] = list.get(i).replace("/", "-");
        }
        String join = String.join(",", fileIdArr);
        System.out.println(join);
    }

    @Test
    public void test6() {
        int[] a = new int[8];
    }

}
