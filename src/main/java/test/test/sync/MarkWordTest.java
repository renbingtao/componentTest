package test.test.sync;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class MarkWordTest {

    @Test
    public void test() {
        Object o = new Object();
        //non-biasable 无锁
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
