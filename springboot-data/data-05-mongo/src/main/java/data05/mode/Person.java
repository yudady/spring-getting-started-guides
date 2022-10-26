package data05.mode;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

    @Id
    public Long id;
    public String name;
    public int age;
    @Version
    public Long version;

    public Person() {
    }

    public Person(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}