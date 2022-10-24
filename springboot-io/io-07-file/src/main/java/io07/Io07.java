package io07;


import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Io07 implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Io07.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        URL resource = Thread.currentThread().getContextClassLoader().getResource("conflict_names.csv");
        assert resource != null;
        URI uri = resource.toURI();


        Path path = Path.of(uri);

        // Path path = .
        List<String> strings = Files.readAllLines(path);

        strings.forEach(System.out::println);


    }

}
