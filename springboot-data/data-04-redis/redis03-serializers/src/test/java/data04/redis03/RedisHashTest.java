package data04.redis03;

import io.github.yudady.util.Strings;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest(classes = data04.redis03.config.Config.class)
@EnabledOnOs(OS.WINDOWS)
@TestMethodOrder(MethodOrderer.MethodName.class)
class RedisHashTest {
    @SpyBean
    RedisTemplate<String, Object> redisTemplate;


    @Test
    void test01_notExist() {
        assertThat(redisTemplate.boundValueOps(this.getClass().getSimpleName()).get(), nullValue());
    }

    @Test
    void test02_set() {
        long now = System.nanoTime();
        String redisKey = Strings.format("redis-hash:{}", now);
        String redisHashKey = Strings.format("redis-hash-key:{}", now);
        String redisHashValue = Strings.format("redis-hash-value:{}", now);

        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(redisKey);
        hashOps.put(redisHashKey, redisHashValue);

    }


}
