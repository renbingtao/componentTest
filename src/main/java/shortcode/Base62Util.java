package shortcode;

public class Base62Util {
    private static final String BASE62_CHARS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    // 编码方法
    public static String encode(long num) {
        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int remainder = (int) (num % 62);
            sb.append(BASE62_CHARS.charAt(remainder));
            num = num / 62;
        }
        return sb.reverse().toString();
    }

}

