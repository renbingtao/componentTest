package rabbitmq.sendConfirm;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class AsyncSend {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.confirmSelect();
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            channel.exchangeDeclare(TASK_QUEUE_NAME, "fanout", true);
            String message = String.join(" ", "Hello World" + new Date());

            //在发布之前，可以通过调用 Channel#getNextPublishSeqNo() 来获取序列号
            long nextNumber = channel.getNextPublishSeqNo();
            System.out.println(nextNumber);

            //还需要将序列号和消息的映射关系记录
            ConcurrentNavigableMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);

            //处理确认的回调
            ConfirmCallback cleanOutstandingConfirms = (sequenceNumber, multiple) -> {
                if (multiple) {
                    ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(
                            sequenceNumber, true
                    );
                    confirmed.clear();
                } else {
                    outstandingConfirms.remove(sequenceNumber);
                }
            };

            //处理异常的回调
            ConfirmCallback cleanOutstandingEx = (sequenceNumber, multiple) -> {
                String body = outstandingConfirms.get(sequenceNumber);
                System.err.format(
                        "Message with body %s has been nack-ed. Sequence number: %d, multiple: %b%n",
                        body, sequenceNumber, multiple
                );
                cleanOutstandingConfirms.handle(sequenceNumber, multiple);
            };

            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));

            //处理发布者确认操作（异步进行）
            //共有 2 个回调函数：一个用于处理已确认的消息，另一个用于处理被拒绝的消息（这些消息可能会被代理服务器视为已丢失）。
            //sequenceNumber：用于标识已确认或已拒绝消息的数字。
            //multiple：如果为假，则仅确认/拒绝一条消息；如果为真，则会确认/拒绝所有序列号小于或等于当前值的消息。
            channel.addConfirmListener(cleanOutstandingConfirms, cleanOutstandingEx);

        }
    }

}
