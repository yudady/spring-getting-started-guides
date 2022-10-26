package data05.mode;

//public record User(Long id , String firstname , String lastname ) {
//}


import org.springframework.data.annotation.Id;

public class User {

    @Id
    public final Long id;
    public final String firstname;
    public final String lastname;

    public User(Long id, String firstname, String lastname) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
