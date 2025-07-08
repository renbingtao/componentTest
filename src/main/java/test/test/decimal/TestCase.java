package test.test.decimal;

import test.test.decimal.util.ReDigitalUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TestCase {

    @Test
    public void test() {
        BigDecimal bigDecimal = new BigDecimal(10341);
        //10.341

        //10.34
        System.out.println(bigDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP));
        //10.34
        System.out.println(bigDecimal.divide(new BigDecimal(1000), 2, 5));
        //10.35
        System.out.println(bigDecimal.divide(new BigDecimal(1000), 2, 0));
        //11
        System.out.println(bigDecimal.divide(new BigDecimal(1000), 0, 0));
    }

    @Test
    public void test2() {
        for (int i = 0; i < 11; i++) {
            System.out.println(100 + i);
            System.out.println(new BigDecimal(100 + i).divide(new BigDecimal(10), 0, BigDecimal.ROUND_UP));
            System.out.println("===============");
        }
    }

    @Test
    public void test3() {
        BigDecimal bigDecimal = new BigDecimal("12.01");
        System.out.println(bigDecimal.toString());
        BigDecimal divide = bigDecimal.divide(new BigDecimal(1), 0, 0);
        System.out.println(divide.toString());
    }

    @Test
    public void test4() {
        BigDecimal bigDecimal = new BigDecimal("12000.5001");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(bigDecimal);
        System.out.println(format);
    }

    @Test
    public void test5() {
        BigDecimal bigDecimal = new BigDecimal("12000.5001");
        int scale = 2;

        String format = "###,##0";
        StringBuilder sb = new StringBuilder(format);
        sb.append(".");
        for (int i = 0; i < scale; ++i) {
            sb.append("0");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        String format1 = df.format(bigDecimal);
        System.out.println(format1);
    }

    @Test
    public void test6() {
        BigDecimal bigDecimal = new BigDecimal("12000.50");
        int precision = bigDecimal.precision();
        System.out.println(precision);
        int scale = bigDecimal.scale();
        System.out.println(scale);
    }

    @Test
    public void test7() {
        BigDecimal bigDecimal = new BigDecimal("12000.50");
        BigDecimal bigDecimal2 = new BigDecimal("12000.500");
        System.out.println(bigDecimal2.equals(bigDecimal));
        int i = bigDecimal.compareTo(bigDecimal2);
        System.out.println(i);
    }

    @Test
    public void test8() {
        ArrayList<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("12"));
        list.add(new BigDecimal("34"));
        list.add(new BigDecimal("63"));
        BigDecimal subRoomAmount = (BigDecimal) list.stream().reduce(ReDigitalUtil::add).orElse(ReDigitalUtil.ZERO);
        System.out.println(subRoomAmount);

        BigDecimal bigDecimal = new BigDecimal("115");
        BigDecimal bigDecimal2 = new BigDecimal("145");
        BigDecimal subtract = ReDigitalUtil.subtract(bigDecimal, bigDecimal2);
        System.out.println(subtract);
    }

    @Test
    public void test9() {
        BigDecimal remainder = new BigDecimal("24").remainder(new BigDecimal(10));
        System.out.println(remainder);
    }

    @Test
    public void test10() {
        BigDecimal remainder = new BigDecimal("10.44").setScale(1, 4);
        System.out.println(remainder);

        BigDecimal remainder2 = new BigDecimal("10.45").setScale(1, 4);
        System.out.println(remainder2);
    }

    @Test
    public void test11() {
        int i = new BigDecimal("10.44").compareTo(new BigDecimal("13.49"));
        System.out.println(i);
    }

}
