package data05.redis04;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data05.redis04.model.User;
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
public class Redis04 implements ApplicationRunner, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Redis04.class);

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(Redis04.class, args);
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
    public void run(String... args) throws Exception {

        User user = new User();
        user.name = "tommy";
        user.age = -20; // @Min(0)


        BoundValueOperations<String, User> valueOps = redisTemplate.boundValueOps("redis04");
        // set
        valueOps.set(user, Duration.ofMinutes(1));
        // get
        User user1 = valueOps.get();

        System.out.println("user1 = " + user1.getClass().getName());

        // =================================================
        // =================================================
        // =================================================
        // =================================================
        // TODO validate
        // 1. 驗證 bean validate.......
        // 2. set in redis
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps("redis04-string");
        // set
        ops.set(objectMapper.writeValueAsString(user), Duration.ofMinutes(1));
        // 3. get in redis
        String userString = ops.get();
        // 4. 驗證 bean validate.......
        User user2 = objectMapper.readValue(userString, User.class);
        System.out.println("user2 = " + user2.getClass().getName());
        System.out.println("user2 = " + user2);

    }

}
