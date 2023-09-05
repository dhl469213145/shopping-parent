package rabbitMQDemo.topic;

import com.framework.rabbitMQDemo.common.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    private static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws Exception {
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        // 3、声明交换器，类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 4、创建消息
        String msg = "send msg by direct type";
        // 5、发送消息
        channel.basicPublish(EXCHANGE_NAME, "update.Name", null,  msg.getBytes());
        channel.close();
        conn.close();
    }
}
