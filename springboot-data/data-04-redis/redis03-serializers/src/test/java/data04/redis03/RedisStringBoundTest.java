package data04.redis03;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = data04.redis03.config.Config.class)
@EnabledOnOs(OS.WINDOWS)
@TestMethodOrder(MethodOrderer.MethodName.class)
class RedisStringBoundTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test01() {
        var boundValueOperations = redisTemplate.boundValueOps(this.getClass().getSimpleName());
        assertThat(boundValueOperations.get(), nullValue());

    }


}
