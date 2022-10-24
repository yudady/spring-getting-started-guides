package io07;


import io07.service.ReadFileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Io07 implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    @Autowired
    ReadFileList readFileList;

    public static void main(String[] args) {
        SpringApplication.run(Io07.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        readFileList.readAllLines();
        readFileList.fileFolderList();
        readFileList.walkPathDepth1();

    }


}
