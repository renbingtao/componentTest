package test.test.calendar;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        // 减去一个小时
        calendar.add(Calendar.HOUR, -1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(time);
        System.out.println(format);
    }

    @Test
    public void test2() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        // 减去一个小时
        calendar.add(Calendar.HOUR, -1);
        Date now = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean before = simpleDateFormat.parse("2024-08-18 20:35:00").before(now);
        System.out.println(before);
    }

    @Test
    public void test3() throws ParseException {
        doTest("YYYY");
        doTest("MM");
        doTest("dd");
        //一年内的第几日
        doTest("DD");
    }

    private void doTest(String format) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String result = simpleDateFormat.format(now);
        System.out.println(result);
    }

    @Test
    public void test4() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.FEBRUARY, 1);
        int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(maxDate);
    }

}
