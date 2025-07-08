package test.test.random;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {

    @Test
    public void test() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }

}
