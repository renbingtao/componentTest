package rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.Date;

public class Confirm2 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.confirmSelect();
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            channel.exchangeDeclare(TASK_QUEUE_NAME, "fanout", true);
            String message = String.join(" ", "Hello World" + new Date().toString());
            for (int i = 0; i < 10; i++) {
                channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            }
            //可以执行多个basicPublish后执行一个waitForConfirmsOrDie，则之前发送的多个消息都会被确认，但出错时无法确认是哪一个消息出错了，因此需要全部重新发布
            channel.waitForConfirmsOrDie(5000);
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

}
