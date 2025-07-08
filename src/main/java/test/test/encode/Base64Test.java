package test.test.encode;

import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {

    @Test
    public void test() {
        String encoding = Base64.getEncoder().encodeToString(("dicjuser" + ":" + "BrGt%dTSX;kZD+3E=cx@P]").getBytes(StandardCharsets.UTF_8));
        System.out.println(encoding);
    }

    @Test
    public void test2() {
        String str = "\"k\": \"dicj_bos_casino\"";
    }

    @Test
    public void test3() throws Exception {
        String str = "中文";
        System.out.println(URLEncoder.encode(str, "utf-8"));
    }

}
