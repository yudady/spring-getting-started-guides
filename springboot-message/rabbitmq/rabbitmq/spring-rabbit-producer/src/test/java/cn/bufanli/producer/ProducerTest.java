package cn.bufanli.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/4 21:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/spring-rabbitmq.xml")
public class ProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplatel;
    @Test
    public void testHelloWord(){
        rabbitTemplatel.convertAndSend("spring_queue","spring");
    }
}
