package cn.bufanli.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅模式
 * @author yd
 * @version 1.0
 * @date 2020/12/4 16:23
 */
public class ProducerRouting {
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
        //5.创建交换机
        /**
         * String exchange, 交换机名称
         * BuiltinExchangeType type{          * 交换机的类型
         *     DIRECT("direct"), 定向
         *     FANOUT("fanout"), 广播 发送到每一个与之绑定的队列
         *     TOPIC("topic"), 通配符
         *     HEADERS("headers") 参数匹配
         *     }
         * boolean durable, 是否持久化
         * boolean autoDelete, 自动删除
         * boolean internal, 内部使用一般false
         * Map<String, Object> arguments 参数列表
         */
        String exchangeName = "test_direct";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,false,null);
        //6.创建队列
        String qu1Name = "test_direct_q1";
        String qu2Name = "test_direct_q2";
        channel.queueDeclare(qu1Name,true,false,false,null);
        channel.queueDeclare(qu2Name,true,false,false,null);
        //7.绑定队列和交换机
        /**
         * String queue, 队列名称
         * String exchange, 交换机名称
         * String routingKey 路由键，绑定规则
         *  如果交换机类型为fanout ，routingKey 为 空字符串
         */
        //队列1的绑定 error
        channel.queueBind(qu1Name,exchangeName,"error");
        //队列2的绑定
        channel.queueBind(qu2Name,exchangeName,"info");
        channel.queueBind(qu2Name,exchangeName,"error");
        channel.queueBind(qu2Name,exchangeName,"warning");
        //8.发送消息
        String body = "日志级别error";
        channel.basicPublish(exchangeName,"info",null,body.getBytes());
        //9.释放资源
        channel.close();
        connection.close();


    }
}
