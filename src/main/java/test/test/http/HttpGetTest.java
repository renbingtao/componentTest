package test.test.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

public class HttpGetTest {

    private static final Log logger = LogFactory.getLog(HttpGetTest.class);

    @Test
    public void test() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder("http://192.168.123.105/api/services/Doc/GetTopFolderId");
            uriBuilder.addParameter("token", "006414b64823ca124def994fc36a6324f40c");
            URI uri = uriBuilder.build();
            HttpGet httpRequest = new HttpGet(uri);

            CloseableHttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();

            String responseBody = EntityUtils.toString(entity);

            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
            System.out.println("Response: " + responseBody);

            httpClient.close();

        } catch (Exception e) {
            logger.warn("do post meet ex!", e);
        }
    }

    @Test
    public void test2() throws IOException {
        HttpPost httpRequest = new HttpPost("http://www.baidu.com");

        RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(6 * 1000).build();
        httpRequest.setConfig(defaultRequestConfig);

        StringEntity requestBody = new StringEntity("abcd", ContentType.APPLICATION_JSON);
        httpRequest.setEntity(requestBody);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpRequest);

        System.out.println(response);
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);
        System.out.println(response.getEntity());
        System.out.println(responseBody);
        System.out.println(httpRequest.toString());
        System.out.println(httpRequest.getEntity());
    }

}
