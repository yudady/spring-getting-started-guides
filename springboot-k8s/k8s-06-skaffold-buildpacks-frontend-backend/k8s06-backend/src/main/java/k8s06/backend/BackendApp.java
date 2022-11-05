package k8s06.backend;

import k8s06.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
