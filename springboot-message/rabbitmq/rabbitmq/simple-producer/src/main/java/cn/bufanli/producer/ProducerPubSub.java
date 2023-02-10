package cn.bufanli.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 訂閱模式
 * @author yd
 * @version 1.0
 * @date 2020/12/4 16:23
 */
public class ProducerPubSub {
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
        //5.創建交換機
        /**
         * String exchange, 交換機名稱
         * BuiltinExchangeType type{          * 交換機的類型
         *     DIRECT("direct"), 定向
         *     FANOUT("fanout"), 廣播 發送到每一個與之綁定的隊列
         *     TOPIC("topic"), 通配符
         *     HEADERS("headers") 參數匹配
         *     }
         * boolean durable, 是否持久化
         * boolean autoDelete, 自動刪除
         * boolean internal, 內部使用一般false
         * Map<String, Object> arguments 參數列表
         */
        String exchangeName = "test_fanout";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,false,null);
        //6.創建隊列
        String qu1Name = "test_fanout_q1";
        String qu2Name = "test_fanout_q2";
        channel.queueDeclare(qu1Name,true,false,false,null);
        channel.queueDeclare(qu2Name,true,false,false,null);
        //7.綁定隊列和交換機
        /**
         * String queue, 隊列名稱
         * String exchange, 交換機名稱
         * String routingKey 路由鍵，綁定規則
         *  如果交換機類型為fanout ，routingKey 為 空字符串
         */
        channel.queueBind(qu1Name,exchangeName,"");
        channel.queueBind(qu2Name,exchangeName,"");
        //8.發送消息
        String body = "日誌級別error";
        channel.basicPublish(exchangeName,"",null,body.getBytes());
        //9.釋放資源
        channel.close();
        connection.close();


    }
}
