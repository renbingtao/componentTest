package test.decimal;

import org.junit.jupiter.api.Test;

public class TryTest {

    @Test
    public void test() {
        System.out.println(doTest());
    }

    private int doTest() {
        int a = 1;
        try {
            a = 2;
            return a;
        } catch (Exception e) {
            a = 3;
            return a;
        } finally {
            a = 4;
        }
    }

    @Test
    public void test2() {
        Student student = doTest2();
        System.out.println(student.age);
        System.out.println(student.name);
    }

    private Student doTest2() {
        Student student = new Student();
        student.age = 1;
        student.name = "a";
        try {
            student.name = "b";
            int a = 1 / 0;
            return student;
        } catch (Exception e) {
//            student.name = "c";
//            return student;
            return setStudentName(student);
        } finally {
//            student.name = "d";
//            return student;
        }
    }

    private Student setStudentName(Student student) {
        student.name = "c";
        return student;
    }

    private static class Student {
        public String name;
        public int age;
    }

}
