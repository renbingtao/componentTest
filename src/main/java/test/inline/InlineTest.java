package test.inline;

public class InlineTest {

    public static void main(String[] args) {
    }

    public void test1() {
        Animal animal = new Cat();
        animal.eat();
        System.out.println("abc");
        animal.eat();
    }

    public void test2() {
        Animal animal = new Cat();
        animal.eat();
        animal = new Dog();
        animal.eat();
    }

    public void test3() {
        Animal animal = new Cat();
        animal.eat();
        Animal animal2 = new Cat();
        animal2.eat();
    }

    public void test4() {
        Animal animal = new Cat();
        animal.eat();
        Cat cat = new Cat();
        cat.eat();
    }

    public void test5() {
        Cat cat = new Cat();
        cat.eat();
        Dog dog = new Dog();
        dog.eat();
    }

}
