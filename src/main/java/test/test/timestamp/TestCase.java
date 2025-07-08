package test.test.timestamp;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

public class TestCase {

    @Test
    public void test() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        long time = instance.getTime().getTime();
        Timestamp currentTimestamp = new Timestamp(time);

        instance.add(Calendar.HOUR, 1);
        System.out.println(instance.getTime());
        long time2 = instance.getTime().getTime();
        Timestamp updateTimestamp = new Timestamp(time2);

        System.out.println(updateTimestamp.after(currentTimestamp));
    }

}
