package test.decimal;

import org.junit.jupiter.api.Test;

public class ByteTest {

    @Test
    public void test() {

        byte a = 127;

        byte b = 127;

        //编译报错
//        a = a + b;

        a += b;
        System.out.println(a);

    }

}
