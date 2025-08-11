package es;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import wsl.WslIpFetcher;

import java.io.IOException;

public class ESRestClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        String wslIpAddress = WslIpFetcher.getWslIpAddress();
        // 1. 创建低级客户端
        try (RestClient restClient = RestClient.builder(new HttpHost(wslIpAddress, 9200, "http")).build()) {
            // 2. 检查集群健康状态（低级API示例）
            Request healthRequest = new Request("GET", "/_cluster/health");
            Response healthResponse = restClient.performRequest(healthRequest);
            System.out.println("Cluster health: " +
                    EntityUtils.toString(healthResponse.getEntity()));
        }
    }

}
