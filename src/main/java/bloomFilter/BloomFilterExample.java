package bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

public class BloomFilterExample {
    public static void main(String[] args) {
        // 创建布隆过滤器，预期插入10000个元素，误判率0.01
        BloomFilter<String> bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                10000,
                0.01
        );

        // 添加元素
        bloomFilter.put("hello");
        bloomFilter.put("world");

        // 检查元素是否存在
        System.out.println("Contains 'hello': " + bloomFilter.mightContain("hello"));
        System.out.println("Contains 'world': " + bloomFilter.mightContain("world"));
        System.out.println("Contains 'java': " + bloomFilter.mightContain("java"));
    }
}
