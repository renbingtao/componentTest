package test.futuretask;

import java.util.concurrent.*;

public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        // 创建Callable任务
        Callable<Integer> task = () -> {
            Thread.sleep(2000);
            return 123;
        };

        // 包装成FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        // 提交到线程池执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);

        System.out.println("get future task response begin");
        // 获取结果（阻塞）
//        System.out.println("结果: " + futureTask.get());
        // 获取结果（超时）
        System.out.println("结果: " + futureTask.get(3, TimeUnit.SECONDS));

        System.out.println("get future task response end");
        executor.shutdown();
    }

}
