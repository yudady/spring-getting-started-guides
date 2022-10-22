package data04;

import io.github.yudady.spring.StringBeans;
import io.github.yudady.util.Exceptions;
import io.github.yudady.util.Strings;
import io.github.yudady.util.Threads;
import java.time.Duration;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class App implements ApplicationRunner, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        StringBeans.getBeanDefinitions(applicationContext)
            .stream().filter(entry -> entry.getKey().toLowerCase(Locale.ROOT).contains("redis")
                || entry.getValue().toString().toLowerCase(Locale.ROOT).contains("redis"))
            .forEach(entry -> System.out.println(
                Strings.format("{} : {}", entry.getKey(), entry.getValue().getClass().getName()))
            );
    }

    @Override
    public void run(String... args) {
        //2、查看容器里面的组件

        for (int i = 0; i < 10; i++) {
            try {
                Threads.sleepRoughly(Duration.ofSeconds(1));
                String key = "" + i;
                stringRedisTemplate.opsForValue().set("data-04-redis:key-" + key, "data-04-redis-value-" + key);
            } catch (Exception e) {
                LOGGER.error(Exceptions.stackTrace(e));
            }

        }
    }
}
