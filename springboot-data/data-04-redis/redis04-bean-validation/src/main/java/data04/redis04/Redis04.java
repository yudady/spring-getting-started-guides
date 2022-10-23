package data04.redis04;

import data04.redis04.model.User;
import io.github.yudady.spring.StringBeans;
import io.github.yudady.util.Strings;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.beans.BeanMap;
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

    public static void main(String[] args) {
        SpringApplication.run(Redis04.class, args);
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


        User user = new User();
        user.name = "tommy";
        user.age = -20; // @Min(0)


        redisTemplate.boundValueOps("redis03").set(user, Duration.ofMinutes(1));

        BoundValueOperations<String, User> valueOps = redisTemplate.boundValueOps("redis04");

        User user1 = valueOps.get();

        System.out.println("user1 = " + user1.getClass().getName());

        // TODO validate

    }

    public static <T> T mapToBean(Map<String, ?> map, Class<T> clazz) {

        try {
            T bean = clazz.getDeclaredConstructor().newInstance();
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
            return bean;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
