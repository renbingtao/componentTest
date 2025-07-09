package rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.Date;

public class Confirm1 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //开启确认模式
            channel.confirmSelect();
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            channel.exchangeDeclare(TASK_QUEUE_NAME, "fanout", true);
            String message = String.join(" ", "Hello World" + new Date().toString());
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            //设置等待超时时间。该方法会一直阻塞
            //一旦消息得到确认，该方法就会返回。如果在超时时间内消息未得到确认，或者被否定应答（意味着由于某种原因代理无法处理它），该方法将抛出异常。
            //超时抛出TimeoutException；消息未被确认抛出IOException；channel未开启确认抛出IllegalStateException
            channel.waitForConfirmsOrDie(5000);
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

}
