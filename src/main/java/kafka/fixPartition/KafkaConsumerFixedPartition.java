package kafka.fixPartition;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import wsl.WslIpFetcher;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static kafka.KafkaProducerExample.KAFKA_TOPIC;

public class KafkaConsumerFixedPartition {
    public static void main(String[] args) throws Exception {

        String wslIpAddress = WslIpFetcher.getWslIpAddress();

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, wslIpAddress + ":9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "quickstart-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //手动指定要消费的分区（例如：消费 KAFKA_TOPIC 的 0 号分区）
        int partition = 0; // 目标分区号
        TopicPartition targetPartition = new TopicPartition(KAFKA_TOPIC, partition);
        // 分配分区（传入包含目标分区的集合）
        consumer.assign(Collections.singletonList(targetPartition));

        // 可选：指定消费起始位置（如从最早位置开始）
        // consumer.seekToBeginning(Collections.singletonList(targetPartition));
        // 或从指定偏移量开始：consumer.seek(targetPartition, 100);

        try {
            //循环消费消息
            while (true) {
                // 拉取消息（超时时间 1 秒）
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                records.forEach(record -> System.out.printf("分区: %d, 偏移量: %d, 键: %s, 值: %s%n", record.partition(), record.offset(), record.key(), record.value()
                ));
                //手动提交偏移量
//                consumer.commitSync();
            }
        } finally {
            //关闭消费者（释放资源）
            consumer.close();
        }
    }


}