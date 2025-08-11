package es;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import wsl.WslIpFetcher;

import java.io.IOException;

public class EsHttpClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        String wslIpAddress = WslIpFetcher.getWslIpAddress();
        // 创建 CloseableHttpClient 实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 HTTP GET 请求
        HttpGet httpGet = new HttpGet("http://" + wslIpAddress + ":9200");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 获取响应状态
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status Code: " + statusCode);
            // 获取响应内容
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                System.out.println("Response Content: " + responseString);
            }
        } catch (IOException e) {
            System.err.println("HTTP请求失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                System.err.println("关闭资源失败: " + e.getMessage());
            }
        }

    }

}
