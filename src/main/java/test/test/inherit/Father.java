package test.test.inherit;

public class Father extends GrandFather{

    @Override
    public void doTest() {
        super.doTest();
        System.out.println("father");
    }

}
