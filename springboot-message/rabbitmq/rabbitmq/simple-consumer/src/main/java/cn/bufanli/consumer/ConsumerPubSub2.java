package cn.bufanli.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/4 16:49
 */
public class ConsumerPubSub2 {
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
        channel.queueDeclare("test_fanout_q2",true,false,false,null);
        //6.接收消息
        /**
         * String queue, 隊列名稱
         * boolean autoAck, 是否自動確認
         * Consumer callback 回調對象
         */
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            /**
             * 回調方法，當收到消息後會自動執行該方法
             * @param consumerTag  消費者標簽
             * @param envelope 獲取一些信息： 交換機 路由key
             * @param properties 配置信息
             * @param body 真實數據
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(consumerTag+":getExchange"+envelope.getExchange()+" properties:"+properties+"body:"+new String(body));
                System.out.println("保存數據庫");
            }
        };
        channel.basicConsume("test_fanout_q2",true, defaultConsumer);
        //7.釋放資源 不需要
        /*channel.close();
        connection.close();*/
    }
}
