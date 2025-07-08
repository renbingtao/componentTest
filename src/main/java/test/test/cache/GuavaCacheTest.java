package test.test.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheTest {
    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5 * 60, TimeUnit.SECONDS)
            .build();

    @Test
    public void test() throws ExecutionException {
        String s = tryCache();
        System.out.println(s);
        System.out.println("==================");
        String s2 = tryCache();
        System.out.println(s2);
    }

    private String tryCache() throws ExecutionException {
        return cache.get("key", () -> getValue("key"));
    }

    private String getValue(String key) {
        System.out.println("get value");
        return key + "_val";
    }

    @Test
    public void test2() {
        String ifPresent = cache.getIfPresent("abc");
        cache.put("abc", "uu");
        String ifPresent2 = cache.getIfPresent("abc");
        cache.invalidateAll();
        String ifPresent3 = cache.getIfPresent("abc");
        System.out.println("abc");
    }

}
