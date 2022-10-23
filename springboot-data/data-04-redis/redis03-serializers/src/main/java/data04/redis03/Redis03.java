package data04.redis03;

import data04.redis03.model.User;
import io.github.yudady.spring.SpringBeans;
import io.github.yudady.util.Strings;
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
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class Redis03 implements ApplicationRunner, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Redis03.class);

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Redis03.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        SpringBeans.getBeanDefinitions(applicationContext)
            .stream().filter(entry -> entry.getKey().toLowerCase(Locale.ROOT).contains("redis")
                || entry.getValue().toString().toLowerCase(Locale.ROOT).contains("redis"))
            .forEach(entry -> System.out.println(
                Strings.format("{} : {}", entry.getKey(), entry.getValue().getClass().getName()))
            );
    }

    @Override
    public void run(String... args) {

        User u = new User();
        u.name = "tommy";
        u.age = 20;

        BoundValueOperations<String, Object> redis03 = redisTemplate.boundValueOps("redis03");
        redis03.setIfAbsent(u, Duration.ofMinutes(1));
        System.out.println("done ========================== ");

    }
}
