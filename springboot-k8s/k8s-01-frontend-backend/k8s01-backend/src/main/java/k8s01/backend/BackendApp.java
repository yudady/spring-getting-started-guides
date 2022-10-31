package k8s01.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import k8s01.backend.service.UserService;

@SpringBootApplication
public class BackendApp implements CommandLineRunner {
    @Autowired
    UserService producerUserService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producerUserService.init();
    }
}
