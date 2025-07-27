package es;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ESRestClientExample {

    public static void main(String[] args) throws IOException {
        // 1. 创建低级客户端
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ).build();

        try {
            // 2. 检查集群健康状态（低级API示例）
            Request healthRequest = new Request("GET", "/_cluster/health");
            Response healthResponse = restClient.performRequest(healthRequest);
            System.out.println("Cluster health: " +
                    EntityUtils.toString(healthResponse.getEntity()));
        } finally {
            restClient.close();
        }
    }

}
