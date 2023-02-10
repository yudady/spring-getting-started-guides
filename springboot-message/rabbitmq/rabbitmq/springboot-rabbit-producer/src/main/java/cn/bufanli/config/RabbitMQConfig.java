package cn.bufanli.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/5 21:24
 */
@Configuration
public class RabbitMQConfig {
    //交换机名称
    public static final String EXCHANGE_NAME = "bufanli_topic_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "boot_queue";

    //声明交换机
    @Bean("bootExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    //声明队列
    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    /**
     * 绑定队列和交换机
     * @param queue 队列
     * @param exchange 交换机
     * @return
     */
    @Bean
    public Binding itemQueueExchange(@Qualifier("bootQueue") Queue queue,
                                     @Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
