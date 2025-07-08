package test.test.staticTest;

import org.junit.jupiter.api.Test;

public class TestA {

    public static void main(String[] args) throws Exception {
//        Class.forName("org.example.staticTest.StaticA");

        Son son = new Son() {
        };

        System.out.println(son.getClass());

        System.out.println(son instanceof Son);

    }

    @Test
    public void test() {
        Son son = new Son();
        System.out.println(son.getClass());

        Son son2 = new Son() {
        };
        Object object = son2.getObject();
        System.out.println(son2.getClass());
    }

}
