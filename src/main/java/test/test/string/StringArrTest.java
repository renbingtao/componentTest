package test.test.string;

import org.junit.Test;

public class StringArrTest {

    @Test
    public void test() {
        String[] aaa = new String[]{"abc", "def"};
        test2(aaa);
        test2("aa", "bb", "cc");
    }

    private void test2(String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

}
