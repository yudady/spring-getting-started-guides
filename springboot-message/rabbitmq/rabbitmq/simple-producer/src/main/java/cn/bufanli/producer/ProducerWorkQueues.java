package cn.bufanli.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 * @author yd
 * @version 1.0
 * @date 2020/12/4 16:23
 */
public class ProducerWorkQueues {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.设置参数
        //2.1 Ip
        connectionFactory.setHost("192.168.1.110");
        //2.2 port
        connectionFactory.setPort(5672);
        //2.3 虚拟机 默认值 /
        connectionFactory.setVirtualHost("/");
        //2.4 username/password 默认值 guest
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //3.创建链接connection
        Connection connection = connectionFactory.newConnection();
        //4.创建channel
        Channel channel = connection.createChannel();
        //5.创建队列queue
        /**
         * queueDeclare（）参数列表
         * String queue, 队列名称 如果没有队列名就创建
         * boolean durable, 是否持久化，当Mq重启之后还在
         * boolean exclusive,  是否只有一个消费者监听这个端口，当链接关闭时是否删除队列
         * boolean autoDelete, 是否自动删除，当没有消费者
         * Map<String, Object> arguments 参数信息
         */
        channel.queueDeclare("work-queues",true,false,false,null);
        //6.发送消息
        /**
         * String exchange, 交换机名称简单模式下交换机会使用默认的
         * String routingKey, 路由名称
         * BasicProperties props, 配值信息
         * byte[] body 真实发送的消息数据
         */
        for (int i = 0; i < 40; i++) {
            String body  = i+ ":Hello rabbitMq -- work-queues";
            channel.basicPublish("","work-queues",null,body.getBytes());
        }
        //7.释放资源
        channel.close();
        connection.close();
    }
}
