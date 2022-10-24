package io01;


import io01.bean.User;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class Io01 implements CommandLineRunner {

	@Autowired
	ApplicationContext context;

	@Override
	public void run(String... args) throws Exception {
		User user = context.getBean(User.class);
		log.info("[LOG] user : {}", user);
	}

	public static void main(String[] args) {
		SpringApplication.run(Io01.class, args);
	}
}
