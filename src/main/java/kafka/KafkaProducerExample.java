package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerExample {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.30.23.131:9092");
        // 键的序列化器（将 String 序列化为字节数组）
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 值的序列化器
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 消息确认级别（-1/all 表示所有 ISR 副本确认，可靠性最高）
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数（发送失败时）
        props.put(ProducerConfig.RETRIES_CONFIG, 3);

        //创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            //发送消息（主题：quickstart-events，键：key-1，值：消息内容）
            for (int i = 0; i < 5; i++) {
                String key = "key-" + i;
                String value = "Hello Kafka! This is message " + i;
                // 创建消息记录
                ProducerRecord<String, String> record = new ProducerRecord<>("quickstart-events", key, value);

                //发送消息的三种模式
                //1.发后即忘:只管发送消息，不关心是否成功，吞吐量最高但可靠性最低
//                producer.send(record);

                //2.同步发送:阻塞等待发送结果，可靠性最高但吞吐量较低
                Future<RecordMetadata> future = producer.send(record);
                RecordMetadata metadata = future.get();
                System.out.printf("发送成功 - 主题：%s，分区：%d，偏移量：%d，消息：%s%n", metadata.topic(), metadata.partition(), metadata.offset(), value);

                //3.异步发送:通过回调函数处理响应，平衡了吞吐量和可靠性
//                producer.send(record, new Callback() {
//                    @Override
//                    public void onCompletion(RecordMetadata metadata, Exception e) {
//                        if (e != null) {
//                            System.err.println("发送失败: " + e.getMessage());
//                        } else {
//                            System.out.printf("发送成功，分区=%d, 偏移量=%d%n", metadata.partition(), metadata.offset());
//                        }
//                    }
//                });
//                // 注意：需要适当延迟关闭或使用flush()
//                producer.flush();

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            producer.close();
        }
    }


}

