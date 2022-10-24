package io07.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class ReadFileList {

    Path currentPath = Path.of("."); // ! hmm...

    public void readAllLines() throws Exception {

        URL resource = Thread.currentThread().getContextClassLoader().getResource("conflict_names.csv");
        assert resource != null;
        URI uri = resource.toURI();


        // Path path = .
        Path of = Path.of(uri);
        List<String> strings = Files.readAllLines(of);

        strings.forEach(System.out::println);

    }

    public void fileFolderList() throws IOException {
        System.out.println("currentPath = " + currentPath.toFile().getAbsolutePath());

        System.out.println("\nFiles.list");
        try (Stream<Path> list = Files.list(currentPath)) {
            list
                .filter(path -> !path.toString().endsWith(".iml"))
                .forEach(System.out::println);
        }
    }

    public void walkPathDepth1() throws Exception {
        System.out.println("currentPath = " + currentPath.toFile().getAbsolutePath());

        try (Stream<Path> walkPath1 = Files.walk(currentPath, 1, FileVisitOption.values())) {
            walkPath1
                //.filter(path -> path.toString().startsWith(".\\src"))
                .forEach(System.out::println);
        }
    }

    public void walkPath2() throws Exception {
        System.out.println("currentPath = " + currentPath.toFile().getAbsolutePath());

        try (Stream<Path> walkPath2 = Files.walk(currentPath, FileVisitOption.values())) {
            walkPath2
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(System.out::println);
        }
    }
}
