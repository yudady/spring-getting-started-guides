package data05;

import data05.mode.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;


import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class Test04_Query {
	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	void test01() {

//		try {
//			BasicQuery query = new BasicQuery("{ age : { $lt : 50 }, accounts.balance : { $gt : 1000.00 }}");
//
//
//			// assertThrows(org.bson.json.JsonParseException.class, () -> mongoTemplate.find(query, Person.class));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


	}

}
