package data05;

import data05.mode.Person;
import data05.mode.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;


import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@SpringBootTest
class Test03_SpringMongoTemplate {
	@Autowired
	MongoTemplate mongoOps;

	@Test
	void test() {

		mongoOps.insert(new Person(System.nanoTime(), "Joe1", 34));

		Person findOne = mongoOps.findOne(new Query(where("name").is("Joe")), Person.class);

		log.info("findOne : {}", findOne);
		mongoOps.findAll(Person.class).forEach(System.out::println);


		mongoOps.insert(new User(System.nanoTime(), "Bob"));
		mongoOps.findAll(User.class).forEach(System.out::println);
	}

}
