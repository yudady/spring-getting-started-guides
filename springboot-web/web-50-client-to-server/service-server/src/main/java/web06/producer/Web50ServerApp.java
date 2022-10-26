package web06.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web06.producer.service.UserService;

@SpringBootApplication
public class Web50ServerApp implements CommandLineRunner {
    @Autowired
    UserService producerUserService;

    public static void main(String[] args) {
        SpringApplication.run(Web50ServerApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producerUserService.init();
    }
}
