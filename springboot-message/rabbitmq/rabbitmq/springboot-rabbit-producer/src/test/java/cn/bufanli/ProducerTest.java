package cn.bufanli;

import cn.bufanli.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/5 21:34
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"boot.hello","boot mq hello ");
    }
}
