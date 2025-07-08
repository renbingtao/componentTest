package test.test.classTest;

public class StaticTest {

    public static int A = 1;

    static {
        A = 2;
    }

    public static void main(String[] args) {
        new StaticTest();
        System.out.println(A);
    }

}
