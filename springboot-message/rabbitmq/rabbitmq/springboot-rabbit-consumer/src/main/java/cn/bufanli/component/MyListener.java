package cn.bufanli.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    /**
     * 监听某个队列的消息
     * @param message 接收到的消息
     */
    @RabbitListener(queues = "boot_queue")
    public void myListener1(String message){
        System.err.println("消费者接收到的消息为：" + message);
    }
}
