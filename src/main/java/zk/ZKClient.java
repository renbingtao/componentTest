package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import wsl.WslIpFetcher;

import java.util.concurrent.CountDownLatch;

public class ZKClient {

    public static void main(String[] args) throws Exception {
        String wslIpAddress = WslIpFetcher.getWslIpAddress();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //new ZooKeeper这个代码是异步执行的，并不是阻塞的，所以这里需要一个CountDownLatch来阻塞
        try (ZooKeeper zk = new ZooKeeper(wslIpAddress + ":2181", 3000, event -> {
            //sleep用来验证连接建立后才执行别的操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("listen");
            countDownLatch.countDown();
        })) {
            countDownLatch.await();
            //判断节点是否存在,不存在返回null
            Stat exists = zk.exists("/test3", null);
            System.out.println("exists:" + exists);

            //创建节点
            zk.create("/test3", "test3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            //注册监听
            //监听节点
            byte[] data = zk.getData("/test3", event -> {
                //这个方法会被执行，因为创建的节点是临时节点，客户端断开时会删除，getData可以监听到节点删除事件，所以触发
                System.out.println("event type:" + event.getType());
            }, null);

            System.out.println("data value:" + new String(data));
        }
    }

}
