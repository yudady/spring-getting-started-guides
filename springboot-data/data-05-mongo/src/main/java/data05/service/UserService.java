package data05.service;

import data05.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public void get(String lastname) {
        boolean b = mongoTemplate.collectionExists(User.class);
        System.out.println("b = " + b);
    }
}
