package learn.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 示例：基本异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });
        //此时任务已经在进行了，不需要像thread.start()

        // 调用get方法会阻塞，等待任务完成
        System.out.println(future.get()); // 输出: Hello World
    }

}
