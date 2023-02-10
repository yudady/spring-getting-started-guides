package cn.bufanli.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/4 16:49
 */
public class ConsumerRouting1 {
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
        channel.queueDeclare("test_direct_q1",true,false,false,null);
        //6.接收消息
        /**
         * String queue, 队列名称
         * boolean autoAck, 是否自动确认
         * Consumer callback 回调对象
         */
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            /**
             * 回调方法，当收到消息后会自动执行该方法
             * @param consumerTag  消费者标签
             * @param envelope 获取一些信息： 交换机 路由key
             * @param properties 配置信息
             * @param body 真实数据
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(consumerTag+":getExchange"+envelope.getExchange()+" properties:"+properties+"body:"+new String(body));
                System.out.println("保存数据库");
            }
        };
        channel.basicConsume("test_direct_q1",true, defaultConsumer);
        //7.释放资源 不需要
        /*channel.close();
        connection.close();*/
    }
}
