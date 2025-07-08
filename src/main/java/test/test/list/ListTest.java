package test.test.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ListTest {

    @Test
    public void test() {

        Student student1 = new Student(1);
        Student student2 = new Student(2);
        Student student3 = new Student(3);

        ArrayList<Student> list = new ArrayList<>();
        list.add(student2);
        list.add(student1);
        list.add(student3);

        for (Student stu : list) {
            System.out.println(stu.getNumber());
        }

        System.out.println("==============");

        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getNumber() - o2.getNumber();
            }
        });

        for (Student stu : list) {
            System.out.println(stu.getNumber());
        }

    }

    @Test
    public void test2() {
        Date date = new Date();

        Date date2 = new Date();
        date2.setTime(date.getTime() - 100000000);

        Date date3 = new Date();
        date3.setTime(date.getTime() - 2 * 100000000);

        ArrayList<Date> list = new ArrayList<>();
        list.add(date2);
        list.add(date);
        list.add(date3);

        for (Date dateObj : list) {
            System.out.println(dateObj);
        }
        System.out.println("==========");

        list.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        for (Date dateObj : list) {
            System.out.println(dateObj);
        }

    }

    @Test
    public void test3() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(null);
        objects.add(null);
        objects.add(null);
        objects.add(null);
        for (Object o : objects) {
            System.out.println("a");
        }
    }

    @Test
    public void test4() {
        ArrayList<Object> list = new ArrayList<>();
        list.get(list.size());
    }

    @Test
    public void test5() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(2);
        objects.add(4);
        objects.add(9);
        for (int i = objects.size() - 1; i >= 0; i--) {
            System.out.println(objects.get(i));
        }
    }

}
