package test.test.set;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest {

    @Test
    public void test() {

//        Set<String> objects = new LinkedHashSet<>();
        Set<String> objects = new HashSet<>();
        objects.add("4fd");
        objects.add("6fd");
        objects.add("3fk");
        objects.add("9uyuyfd");
        objects.add("2224fd");


        for (String val : objects) {
            System.out.println(val);
        }
    }

}
