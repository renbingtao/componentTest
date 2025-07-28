package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class ZKClient {

    public static void main(String[] args) throws Exception {
        try (ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, event -> {
            System.out.println("listen");
        })) {
            //判断节点是否存在,不存在返回null
            Stat exists = zk.exists("/test3", null);
            System.out.println("exists:" + exists);

            //创建节点
            zk.create("/test3", "test3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            //注册监听
            byte[] data = zk.getData("/test3", new Watcher() {//监听节点
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("event type" + event.getType());
                }
            }, null);

            System.out.println("data value:" + new String(data));
        }
    }

}
