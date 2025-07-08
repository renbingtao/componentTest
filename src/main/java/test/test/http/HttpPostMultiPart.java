package test.test.http;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpPostMultiPart {

    @Test
    public void test() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", "0034316cd41616e54b698ed80fc717db6bf1");
        map.put("uploadId", UUID.randomUUID().toString());
        map.put("regionHash", "cb0e0faa36a1459bb109ad1b1ecc1688|eyJSZWdpb25JZCI6MSwiUmVnaW9uVXJsIjoiIiwiUGFyZW50Rm9sZGVySWQiOjE2MiwiRmlsZUlkIjoyMjQsIkZpbGVWZXJJZCI6MjUxLCJGaWxlU2l6ZSI6OTU5OCwiSXNVcGRhdGUiOmZhbHNlLCJGaWxlRW50aXR5SWQiOiI4YmZiMTIyNDk5NzE0OGM2YjIzYjMzOTlhYzBjNThiZCIsIlN0b3JhZ2VQYXRoIjoiL2Vkb2MyRG9jcy9EZWZhdWx0U3RvcmFnZTEvOGIvZmIvMTIvOGJmYjEyMjQ5OTcxNDhjNmIyM2IzMzk5YWMwYzU4YmQueGxzeCIsIlN0b3JhZ2VQbGF0Zm9ybSI6MCwiU3RvcmFnZUlkIjoxLCJJc0NoZWNrT3V0IjpmYWxzZX0=");
        map.put("regionId", 1);
        map.put("size", 9598);
        map.put("blockSize", 9598);

        CloseableHttpClient httpClient = null;

        httpClient = HttpClients.createDefault();
        HttpPost httpRequest = new HttpPost("http://" + "192.168.123.105" + "/document/upload");

        ContentType contentType = ContentType.create("multipart/form-data", Consts.UTF_8);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .setContentType(ContentType.MULTIPART_FORM_DATA)
                .setCharset(Consts.UTF_8);
        for (Map.Entry<String, Object> bodyPart : map.entrySet()) {
            StringBody bodyValue = new StringBody(String.valueOf(bodyPart.getValue()), contentType);
            multipartEntityBuilder.addPart(bodyPart.getKey(), bodyValue);
        }

        FileInputStream file = new FileInputStream("C:\\Users\\rbt\\Desktop\\博监-税收计算.xlsx");

        multipartEntityBuilder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, "博监-税收计算.xlsx");

        HttpEntity bodyEntity = multipartEntityBuilder.build();

        httpRequest.setEntity(bodyEntity);

        //
        Header[] headers = httpRequest.getHeaders("Content-Type");

        CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        HttpEntity entity = httpResponse.getEntity();
        String responseBody = EntityUtils.toString(entity);

        System.out.println(responseBody);

    }

}
