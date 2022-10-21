package data04;

import io.github.yudady.util.Exceptions;
import java.time.Duration;

import io.github.yudady.util.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class App implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);


    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 0; i < 10; i++) {
            try {
                Threads.sleepRoughly(Duration.ofSeconds(1));
                String key = "" + i;
                stringRedisTemplate.opsForValue().set("data-04-redis-key-" + key, "data-04-redis-value-" + key);
                System.out.println("key = " + key);
            } catch (Exception e) {
                LOGGER.error(Exceptions.stackTrace(e));
            }

        }

    }
}
