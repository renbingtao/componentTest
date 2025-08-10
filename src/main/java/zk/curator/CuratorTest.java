package zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import wsl.WslIpFetcher;

import java.util.concurrent.TimeUnit;

public class CuratorTest {

    public static void main(String[] args) throws Exception {
        String wslIpAddress = WslIpFetcher.getWslIpAddress();
        // 1. 初始化Curator客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(wslIpAddress + ":2181")
                .retryPolicy(retryPolicy)
                .namespace("MyApp") // 命名空间隔离
                .build();
        client.start();

        // 2. 创建分布式锁实例
        InterProcessMutex lock = new InterProcessMutex(client, "/locks/orderLock");

        // 3. 获取锁并执行业务逻辑
        try {
            if (lock.acquire(10, TimeUnit.SECONDS)) { // 支持超时等待
                try {
                    System.out.println("获取锁成功，执行业务...");
                    Thread.sleep(2000); // 模拟业务处理
                } finally {
                    lock.release(); // 必须显式释放
                }
            } else {
                System.out.println("获取锁超时");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
