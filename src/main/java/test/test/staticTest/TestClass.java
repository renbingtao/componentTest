package test.test.staticTest;

public class TestClass {

    static {
        System.out.println("static");
    }

    {
        System.out.println("non-static");
    }

    TestClass() {
        System.out.println("constructor");
    }

}
