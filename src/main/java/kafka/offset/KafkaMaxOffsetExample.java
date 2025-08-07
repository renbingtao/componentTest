package kafka.offset;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import wsl.WslIpFetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static kafka.KafkaProducerExample.KAFKA_TOPIC;

public class KafkaMaxOffsetExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        String wslIpAddress = WslIpFetcher.getWslIpAddress();

        // 配置消费者属性
        Properties props = new Properties();
        props.put("bootstrap.servers", wslIpAddress + ":9092");
        props.put("group.id", "quickstart-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 创建消费者实例

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // 获取主题的所有分区
            List<TopicPartition> partitions = new ArrayList<>();
            consumer.partitionsFor(KAFKA_TOPIC).forEach(partitionInfo ->
                    partitions.add(new TopicPartition(KAFKA_TOPIC, partitionInfo.partition()))
            );

            // 获取所有分区的最大偏移量
            Map<TopicPartition, Long> endOffsets = consumer.endOffsets(partitions);

            // 打印每个分区的最大偏移量
            for (Map.Entry<TopicPartition, Long> entry : endOffsets.entrySet()) {
                TopicPartition partition = entry.getKey();
                long maxOffset = entry.getValue();
                // 最后一条消息的实际偏移量是 maxOffset - 1
                System.out.printf("分区 %d 的最大偏移量: %d (最后一条消息偏移量: %d)%n", partition.partition(), maxOffset, maxOffset - 1);
            }
        }
        // 关闭消费者
    }
}

