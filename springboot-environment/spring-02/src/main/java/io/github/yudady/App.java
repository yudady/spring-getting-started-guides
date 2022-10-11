package io.github.yudady;


import java.util.Arrays;

import io.github.yudady.bean.User;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	ApplicationContext context;

	@Override
	public void run(String... args) {
		String[] beanNamesForType = context.getBeanNamesForType(User.class);
		Arrays.stream(beanNamesForType).forEach(beanName -> {
			User bean = context.getBean(beanName, User.class);
			log.info("[LOG] user : {} , {}", beanName, bean);
		});

	}

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
		// Arrays.stream(run.getBeanDefinitionNames()).forEach(log::info);
	}
}
