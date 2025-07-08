package test.test.json;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

public class FastJsonTest {

    @Test
    public void test() {
        Entity1 entity1 = new Entity1();
        Entity2 entity2 = new Entity2();
        entity1.setTest2(entity2);
        entity2.setTest1(entity1);
        String jsonString = JSON.toJSONString(entity1);
        System.out.println(jsonString);
    }

}
