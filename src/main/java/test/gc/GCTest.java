package test.gc;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

public class GCTest {

    //不断创建对象并销毁，模拟连续请求的场景
    //jstat -gc pid 1000 命令每1秒打印jvm内存情况。发现伊甸区使用率上升，至100发生young gc，伊甸区使用率归0，再重复
    //由此可见jvm不会主动回收内存，而是当伊甸区满了才触发young gc
    @Test
    public void test() throws Exception {
        //打印进程id
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        String processId = runtimeMxBean.getName().split("@")[0];
        System.out.println("Process ID: " + processId);

        List[] listArr = new List[100000];
        for (int i = 0; i < listArr.length; i++) {
            ArrayList<Object> list = new ArrayList<>();
            for (int j = 0; j < 200; j++) {
                list.add(j);
            }
            listArr[i] = list;
            if (i != 0) {
                listArr[i - 1] = null;
            }
            Thread.sleep(10);
        }
        System.out.println("end");
    }

}
