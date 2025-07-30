package zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZKWatcherTest2 {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try (ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, event -> {
            countDownLatch.countDown();
            System.out.println("event type:" + event.getType());
        })) {
            countDownLatch.await();
            String path = "/test_persistent";
            //不存在则创建节点
            if (zk.exists(path, null) == null) {
                zk.create(path, path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //注册监听
            byte[] data = zk.getData(path, true, null);

            String newValue = "new value";
            zk.setData(path, newValue.getBytes(), -1);

            String newValue2 = "new value2";
            zk.setData(path, newValue2.getBytes(), -1);

            String newValue3 = "new value3";
            zk.setData(path, newValue3.getBytes(), -1);

//            上述代码执行后，打印如下
//            event type:None   这是建立连接时触发的
//            event type:NodeDataChanged    这是set = newValue时触发的，由于是一次性，用后删除，后面set = newValue2/newValue3时不再触发
//            event type:None   这是断开连接时触发的
            Thread.sleep(1000);
        }

    }

}
