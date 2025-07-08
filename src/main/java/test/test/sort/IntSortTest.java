package test.test.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class IntSortTest {

    @Test
    public void test() {

        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(3);
        objects.add(6);
        objects.add(4);

        objects.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer one, Integer other) {
                if (one == null && other == null) {
                    return 0;
                } else if (one == null) {
                    return 1;
                } else {
                    return other == null ? -1 : other.compareTo(one);
                }
            }
        });

        for (int i = 0; i < objects.size(); i++) {
            System.out.println(objects.get(i));
        }

    }

}
