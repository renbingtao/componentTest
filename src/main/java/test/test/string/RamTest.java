package test.test.string;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RamTest {

    @Test
    public void test() throws Exception {
        //打印进程id
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        String processId = runtimeMxBean.getName().split("@")[0];
        System.out.println("Process ID: " + processId);
        for (int i = 0; i < 10000000 * 10; i++) {
            String a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + i + "bbbbbbbbbbbbbbbbbb" + i * i;
        }
        System.out.println("end");
        Thread.sleep(1000000000);
    }

}
