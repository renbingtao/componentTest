package test.test.StringBuilder;

import org.junit.jupiter.api.Test;

public class StringBuilderTest {

    @Test
    public void test() {

        StringBuilder stringBuilder = new StringBuilder();

        String string = stringBuilder.toString();

        System.out.println(string);

    }

    @Test
    public void test2(){
        StringBuilder stringBuilder = new StringBuilder("abc");

        StringBuilder newStr = stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        System.out.println(stringBuilder.toString());
        System.out.println(newStr.toString());

    }

}
