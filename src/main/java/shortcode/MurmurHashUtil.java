package shortcode;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class MurmurHashUtil {

    public static long hash(String longUrl) {
        // 使用Guava的MurmurHash3算法 murmurhash有32和128两种
        return Hashing.murmur3_32_fixed()
                .hashString(longUrl, StandardCharsets.UTF_8)
                .padToLong();
    }

}
