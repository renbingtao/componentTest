package zk.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DistributedLock {

    private ZooKeeper zk;
    private String lockPath;
    private String currentLock;
    private CountDownLatch connectLatch = new CountDownLatch(1);
    private volatile CountDownLatch waitLatch;

    public DistributedLock(String zkAddress, String lockPath) throws Exception {
        this.lockPath = lockPath;
        this.zk = new ZooKeeper(zkAddress, 30000, event -> {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectLatch.countDown();
            }
        });
        connectLatch.await();

        Stat stat = zk.exists(lockPath, false);
        if (stat == null) {
            zk.create(lockPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    public void lock() throws Exception {
        while (true) {
            currentLock = zk.create(lockPath + "/lock-", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            List<String> children = zk.getChildren(lockPath, false);
            Collections.sort(children);

            String currentNode = currentLock.substring(lockPath.length() + 1);
            int index = children.indexOf(currentNode);

            if (index == 0) {
                return; // 成功获取锁
            } else {
                String prevNode = lockPath + "/" + children.get(index - 1);
                waitLatch = new CountDownLatch(1);

                Stat prevStat = zk.exists(prevNode, event -> {
                    if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                        waitLatch.countDown();
                    }
                });

                if (prevStat != null) {
                    waitLatch.await(); // 等待前驱节点释放
                }
                // 重新检查锁状态，避免前驱节点异常断开导致的误判
            }
        }
    }

    public void unlock() throws Exception {
        if (currentLock != null) {
            zk.delete(currentLock, -1);
            currentLock = null;
        }
    }

    public void close() throws Exception {
        zk.close();
    }
}
