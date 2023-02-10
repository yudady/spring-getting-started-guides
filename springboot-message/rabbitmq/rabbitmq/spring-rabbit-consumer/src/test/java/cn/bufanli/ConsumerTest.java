package cn.bufanli;

import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yd
 * @version 1.0
 * @date 2020/12/5 20:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/spring-rabbitmq.xml")
public class ConsumerTest {
    @Test
    public void test1(){
        boolean flag =true;
        while (flag){

        }
    }
}
