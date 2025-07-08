package test.test.enumTest;

public enum SimpleEnum {
    ;

    private String name;

    SimpleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
