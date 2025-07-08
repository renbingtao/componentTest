package test.test.number;

import org.junit.Test;

public class IntTest {

    @Test
    public void test() {
        int version = 99;
        String expectedName = String.format("【已退回-%s】", version);
        System.out.println(expectedName);
    }

}
