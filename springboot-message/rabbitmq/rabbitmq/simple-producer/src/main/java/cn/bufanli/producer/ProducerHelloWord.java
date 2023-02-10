package cn.bufanli.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 發送消息
 *
 * @author yd
 * @version 1.0
 * @date 2020/12/4 16:23
 */
public class ProducerHelloWord {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.創建連接工廠
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.設置參數
        //2.1 Ip
        connectionFactory.setHost("localhost");
        //2.2 port
        connectionFactory.setPort(5672);
        //2.3 虛擬機 默認值 /
        connectionFactory.setVirtualHost("/");
        //2.4 username/password 默認值 guest
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //3.創建鏈接connection
        Connection connection = connectionFactory.newConnection();
        //4.創建channel
        Channel channel = connection.createChannel();
        //5.創建隊列queue
        /**
         * queueDeclare（）參數列表
         * String queue, 隊列名稱 如果沒有隊列名就創建
         * boolean durable, 是否持久化，當Mq重啟之後還在
         * boolean exclusive,  是否隻有一個消費者監聽這個端口，當鏈接關閉時是否刪除隊列
         * boolean autoDelete, 是否自動刪除，當沒有消費者
         * Map<String, Object> arguments 參數信息
         */
        channel.queueDeclare("hello-word", true, false, false, null);
        //6.發送消息
        /**
         * String exchange, 交換機名稱簡單模式下交換機會使用默認的
         * String routingKey, 路由名稱
         * BasicProperties props, 配值信息
         * byte[] body 真實發送的消息數據
         */
        String body = "Hello rabbitMq";
        channel.basicPublish("", "hello-word", null, body.getBytes());
        //7.釋放資源
        channel.close();
        connection.close();
    }
}
