package kafka.fixPartition;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.log4j.Logger;
import wsl.WslIpFetcher;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GetAllPartitions {

    private static final Logger logger = Logger.getLogger(GetAllPartitions.class);

    public static void main(String[] args) throws Exception {

        String wslIpAddress = WslIpFetcher.getWslIpAddress();

        // 1. 配置 AdminClient 属性
        Properties props = new Properties();
        // Kafka 集群地址（替换为实际地址）
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, wslIpAddress + ":9092");

        // 2. 创建 AdminClient 实例
        try (AdminClient adminClient = AdminClient.create(props)) {
            // 要查询的主题名（替换为实际主题）
            String topicName = "test-topic";

            // 3. 查询主题元数据
            DescribeTopicsResult describeResult = adminClient.describeTopics(
                    Collections.singletonList(topicName),
                    new DescribeTopicsOptions().timeoutMs(5000) // 超时时间
            );

            // 4. 获取主题描述信息（阻塞等待结果）
            KafkaFuture<Map<String, TopicDescription>> future = describeResult.allTopicNames();
            Map<String, TopicDescription> topicDescriptions = future.get();

            // 5. 提取分区号
            TopicDescription topicDesc = topicDescriptions.get(topicName);
            if (topicDesc == null) {
                System.out.println("主题不存在: " + topicName);
                return;
            }

            // 遍历所有分区，获取分区号
            System.out.println("主题 " + topicName + " 的所有分区号：");
            topicDesc.partitions().forEach(partitionInfo -> {
                int partitionId = partitionInfo.partition();
                System.out.println(partitionId);
            });

        } catch (InterruptedException | ExecutionException e) {
            logger.error("GetAllPartitions meet ex!e={}", e);
        }
    }
}
