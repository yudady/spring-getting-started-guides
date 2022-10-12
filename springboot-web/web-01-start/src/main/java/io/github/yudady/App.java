package io.github.yudady;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class App implements ApplicationRunner, CommandLineRunner {

	@Autowired
	ApplicationContext context;


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("CommandLineRunner ===============");
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("ApplicationRunner ===============");
	}
}
