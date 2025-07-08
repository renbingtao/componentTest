package test.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void test() {
        task(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
    }

    private void task(Consumer consumer) {
        for (int i = 0; i < 3; i++) {
            consumer.accept(123);
        }
    }


}
