package tk.tommy.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class BookTests {
    // FIXME

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
        .withUsername("duke")
        .withPassword("password")
        .withDatabaseName("test");

    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {

        Book book = new Book();

        book.name = "Testcontainers";

        bookRepository.save(book);

        System.out.println("Context loads!");
    }

}