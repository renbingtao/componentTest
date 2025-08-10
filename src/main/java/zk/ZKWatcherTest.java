package zk;

import org.apache.zookeeper.*;
import wsl.WslIpFetcher;

import java.util.concurrent.CountDownLatch;

public class ZKWatcherTest {

    public static void main(String[] args) throws Exception {
        String wslIpAddress = WslIpFetcher.getWslIpAddress();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try (ZooKeeper zk = new ZooKeeper(wslIpAddress + ":2181", 3000, event -> {
            countDownLatch.countDown();
        })) {
            countDownLatch.await();
            String path = "/test_persistent";
            //不存在则创建节点
            if (zk.exists(path, null) == null) {
                zk.create(path, path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //注册监听
            byte[] data = zk.getData(path, new Watcher() {//监听节点
                @Override
                public void process(WatchedEvent event) {
                    //set了两次，但该方法只会执行一次
                    System.out.println("event type:" + event.getType());
                }
            }, null);

            String newValue = "new value";
            zk.setData(path, newValue.getBytes(), -1);

            String newValue2 = "new value2";
            zk.setData(path, newValue2.getBytes(), -1);

            Thread.sleep(1000);
        }

    }

}
