package test.test.date;


import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestFormat {

    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date parse = simpleDateFormat.parse("2023-11");

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年MM月");
        System.out.println(simpleDateFormat2.format(parse));
    }

    @Test
    public void test2() throws ParseException {
        String date = "2023-11-23 00:00:00.0";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format.equals(date.split(" ")[0]));
    }

    @Test
    public void test3() throws ParseException {
        String date = "2023-12-17 00:00:00.0";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format.equals(date.split(" ")[0]));
    }

    @Test
    public void test4() throws ParseException {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getYear() + 1900);
        System.out.println(date.getMonth() + 1);
        System.out.println(date.getDate());
    }

    @Test
    public void test5() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }

    @Test
    public void test6() {
        int minute = 15;
        Calendar calendar = Calendar.getInstance();
        // 减去一个小时
        calendar.add(Calendar.MINUTE, (-1) * minute);
        Date time = calendar.getTime();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        System.out.println(format);
    }

    @Test
    public void test7() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        //正常
//        Date date = simpleDateFormat.parse("2024-02-10");
        Date date = simpleDateFormat.parse("2024-02-30");
        System.out.println(date.toString());
    }

}
