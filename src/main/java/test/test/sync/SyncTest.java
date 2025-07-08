package test.test.sync;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/*
4秒前状态是无锁					0x0000000000000001 (non-biasable; age: 0)
4秒后状态可偏向的锁				0x0000000000000005 (biasable; age: 0)
只有一个线程获取锁，状态为已偏向	0x00000229bf87d805 (biased: 0x000000008a6fe1f6; epoch: 0; age: 0)
上面的线程释放锁，但状态不变		0x00000229bf87d805 (biased: 0x000000008a6fe1f6; epoch: 0; age: 0)
多个线程竞争时，先使用cas轻量级锁，超时后改为重量级锁。退出后仍是重量级锁。
*/

public class SyncTest {

    @Test
    public void testSingle() throws Exception {
        Thread.sleep(4200);

        Object object = new Object();
        System.out.println("before lock");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println("get lock");
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }

        Thread.sleep(2000);
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

    }

    @Test
    public void testMulti() throws Exception {
        Thread.sleep(4200);

        Object object = new Object();
        System.out.println("before lock");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (object) {
                    System.out.println(" thread1 get lock");
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(" thread1 release lock");
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (object) {
                    System.out.println(" thread2 get lock");
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

        Thread.sleep(3000);
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println("main get lock");
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }

    }

    @Test
    public void testRotation() throws Exception {
        Object object = new Object();
        //0x0000000000000001 (non-biasable; age: 0) 表示无锁
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object) {
            Thread.sleep(50);
            //0x00000069ea8fe2b0 (thin lock: 0x00000069ea8fe2b0) 表示轻量级锁
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
        Thread.sleep(50);
        //0x0000000000000001 (non-biasable; age: 0)
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    //需要添加-XX:+UseBiasedLocking
    @Test
    public void testRotation2() throws Exception {
        Object object = new Object();
        Thread.sleep(4200);
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("=========1=========");
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (object) {
                    System.out.println("=========2=========");
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                }
            }
        }.start();

        Thread.sleep(200);
    }

}
