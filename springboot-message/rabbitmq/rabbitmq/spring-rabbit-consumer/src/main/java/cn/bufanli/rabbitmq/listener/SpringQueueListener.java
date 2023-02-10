package cn.bufanli.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/5 20:55
 */
public class SpringQueueListener implements MessageListener {
    public void onMessage(Message message) {
        System.out.println(message.getBody());
    }
}
