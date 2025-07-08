package test.test.longtest;

import org.junit.jupiter.api.Test;

public class LongTest {

    @Test
    public void test() {

        Long l = (long) 0;

        System.out.println(l == 0);
        System.out.println(l.equals(0));
        System.out.println(l.equals((long) 0));

    }

}
