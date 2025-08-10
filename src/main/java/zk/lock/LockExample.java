package zk.lock;

import wsl.WslIpFetcher;

public class LockExample {

    public static void main(String[] args) throws Exception {
        String wslIpAddress = WslIpFetcher.getWslIpAddress();
        DistributedLock lock = new DistributedLock(wslIpAddress + ":2181", "/locks");
        try {
            lock.lock();
            // 执行需要加锁的业务逻辑
            System.out.println("获取锁成功，执行业务逻辑");
            Thread.sleep(2000);
        } finally {
            lock.unlock();
            System.out.println("释放锁");
        }
    }

}
