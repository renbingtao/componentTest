package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static kafka.KafkaProducerExample.KAFKA_IP;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // 1. 配置消费者属性
        Properties props = new Properties();
        // Kafka 服务器地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_IP + ":9092");
        // 消费者组 ID（同一组内的消费者分担消费分区）
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "quickstart-group");
        // 键的反序列化器（将字节数组反序列化为 String）
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 值的反序列化器
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 自动提交偏移量（默认 true，建议生产环境用手动提交）
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // 自动提交偏移量的间隔（毫秒）
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 首次消费时，从最早的消息开始（earliest/latest/none）
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 2. 创建消费者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        try {
            // 3. 订阅主题（可订阅多个，用集合传入）
            consumer.subscribe(Collections.singletonList("quickstart-events"));

            // 4. 循环拉取消息（长轮询）
            while (true) {
                // 拉取消息，超时时间 1000 毫秒
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                // 处理消息
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("接收消息 - 主题：%s，分区：%d，偏移量：%d，键：%s，值：%s%n",
                            record.topic(), record.partition(), record.offset(),
                            record.key(), record.value());
                }
                //手动提交偏移量
                consumer.commitSync();
            }
        } finally {
            // 5. 关闭消费者（释放资源）
            consumer.close();
        }
    }
}

