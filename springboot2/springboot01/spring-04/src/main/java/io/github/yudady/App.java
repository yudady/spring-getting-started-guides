package io.github.yudady;


import java.util.Arrays;

import io.github.yudady.bean.User;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(User.class)
public class App implements CommandLineRunner {

	@Autowired
	ApplicationContext context;
	@Autowired
	Environment e;

	@Override
	public void run(String... args) {
		String[] beanNamesForType = context.getBeanNamesForType(User.class);
		Arrays.stream(beanNamesForType).forEach(beanName -> {
			User bean = context.getBean(beanName, User.class);
			log.info("[LOG] user : {} , {}", beanName, bean);
		});

		System.out.println("===================================");
		Arrays.stream(e.getActiveProfiles()).forEach(System.out::println);
	}

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(App.class, args)));
	}
}
