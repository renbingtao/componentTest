package test.test.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    @Test
    public void test() throws InterruptedException {
        int coreThread = 3;
//        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(coreThread, coreThread,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.DiscardPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 1; i < coreThread + 5; i++) {
            doTask(executorService, i);
        }
        Thread.sleep(10000);
    }

    private void doTask(ExecutorService executorService, Object param) {

//        executorService.submit(new Runnable() {
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println(param);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

}
