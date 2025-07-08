package test.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    public void test() throws InterruptedException {
        // 将 i 声明为 final 类型
        final int i = 10;
        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    int j = 0;
                    while (j <= i) {
                        System.out.println("1");
                        j++;
                        try {
                            // 唤醒其他等待的线程
                            lock.notify();
                            // 无超时等待
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    // 最后一次唤醒其他可能等待的线程
                    lock.notify();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    int j = 0;
                    while (j <= i) {
                        System.out.println("2");
                        j++;
                        try {
                            // 唤醒其他等待的线程
                            lock.notify();
                            // 无超时等待
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    // 最后一次唤醒其他可能等待的线程
                    lock.notify();
                }
            }
        });
        t2.start();

        t1.join();
        t2.join();
        System.out.println("end");
    }
}