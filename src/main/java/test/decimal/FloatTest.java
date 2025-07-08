package test.decimal;

import org.junit.Test;

import java.math.BigDecimal;

public class FloatTest {

    //    浮点数无法精确保存0.3，打印出来为0.3是经过格式化处理的
    @Test
    public void test() {
        float f = 0.3f;
        BigDecimal bigDecimal = new BigDecimal(f);
        System.out.println(f);
        System.out.println(bigDecimal);
    }

    //0.1和0.3都无法精确保存，因此结果不同
    @Test
    public void test2() {
        System.out.println(new BigDecimal(0.1 * 3));
        System.out.println(new BigDecimal(0.3));
    }

    //0.25及0.75可以精确保存，因此相等
    @Test
    public void test3() {
        System.out.println(new BigDecimal(0.75));
        System.out.println(new BigDecimal(0.25 * 3));
    }

}
