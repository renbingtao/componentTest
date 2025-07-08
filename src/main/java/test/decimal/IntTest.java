package test.decimal;

import org.junit.jupiter.api.Test;

public class IntTest {

    @Test
    public void test() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        a = a + b;
        a += b;
        System.out.println(a);
    }

}
