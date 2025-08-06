package shortcode;

public class ShortCodeTest {

    public static void main(String[] args) {
        String originUrl = "https://www.example.com/very/long/url/path/12";
        long hashTmp = MurmurHashUtil.hash(originUrl);
        System.out.println(hashTmp);
        long hashfinal = hashTmp < 0 ? -hashTmp : hashTmp;
        String encode = Base62Util.encode(hashfinal);
        System.out.println(encode);
    }

}
