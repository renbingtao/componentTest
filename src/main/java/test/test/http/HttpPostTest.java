package test.test.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class HttpPostTest {

    private static final Log logger = LogFactory.getLog(HttpPostTest.class);

    @Test
    public void test() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpRequest = new HttpPost("http://192.168.123.105/api/services/Org/UserLogin");

            StringEntity requestBody = new StringEntity("{\"userName\": \"kingdee01\", \"password\": \"edoc2edoc2\"}", ContentType.APPLICATION_JSON);
            httpRequest.setEntity(requestBody);

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
        CloseableHttpResponse res = getRes();
        int statusCode = res.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        String responseBody = EntityUtils.toString(res.getEntity());
        System.out.println(responseBody);
    }

    private CloseableHttpResponse getRes() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpRequest = new HttpPost("http://192.168.123.105/api/services/Org/UserLogin");
            StringEntity requestBody = new StringEntity("{\"userName\": \"kingdee01\", \"password\": \"edoc2edoc2\"}", ContentType.APPLICATION_JSON);
            httpRequest.setEntity(requestBody);
            CloseableHttpResponse response = httpClient.execute(httpRequest);
            httpClient.close();
            return response;
        } catch (Exception e) {
            logger.warn("do post meet ex!", e);
            return null;
        }
    }

    @Test
    public void test3() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder("http://192.168.123.105/api/services/Org/UserLogin");

            HttpPost httpRequest = new HttpPost("http://192.168.123.105/api/services/Org/UserLogin");

            StringEntity requestBody = new StringEntity("{\"userName\": \"kingdee01\", \"password\": \"edoc2edoc2\"}", ContentType.APPLICATION_JSON);
            httpRequest.setEntity(requestBody);

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
    public void test4() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpRequest = new HttpPost("http://192.168.123.105/WebCore");

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            StringBody regionDocOperationApi = new StringBody("RegionDocOperationApi", ContentType.TEXT_PLAIN);
            multipartEntityBuilder.addPart("module", regionDocOperationApi);

            HttpEntity build = multipartEntityBuilder.build();

            httpRequest.setEntity(build);

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
    public void test5() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("http://127.0.0.1:8181/student2");
        uriBuilder.addParameter("name", "abc");
        URI uri = uriBuilder.build();

        ArrayList<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("name", "aa"));
        paramList.add(new BasicNameValuePair("classroom", "bb"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "UTF-8");

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(urlEncodedFormEntity);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        HttpEntity entity = httpResponse.getEntity();
        String responseBody = EntityUtils.toString(entity);
        System.out.println(responseBody);
    }

}
