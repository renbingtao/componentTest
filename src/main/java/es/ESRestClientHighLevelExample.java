package es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESRestClientHighLevelExample {

    public static void main(String[] args) throws IOException {
        // 1. 创建高级客户端（推荐）
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200))
        );

        try {
            // 2. 索引文档（高级API示例）
            IndexRequest request = new IndexRequest("products")
                    .id("1")
                    .source("name", "Elasticsearch Guide",
                            "price", 49.99,
                            "tags", new String[]{"search", "database"});
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            System.out.println("Indexed doc ID: " + response.getId());

        } finally {
            // 3. 关闭连接
            client.close();
        }
    }

}
