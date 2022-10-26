package data05;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class Test04_SpringMongoClient {

	//	@Autowired
//	MongoTemplate mongoOps;
	@Autowired
	MongoClient mongoClient;

	@Test
	void initMongoClients() {
//		MongoClient mongoClient = MongoClients.create("mongodb://test:test@localhost:27017/test");

		MongoIterable<String> strings = mongoClient.listDatabaseNames();
		for (String mongo : strings) {
			System.out.println("mongo = " + mongo);
		}

//		MongoOperations mongoOps = new MongoTemplate(mongoClient, "database");
//		mongoOps.insert(new Person("Joe", 34));
//
//		Person findOne = mongoOps.findOne(new Query(where("name").is("Joe")), Person.class);
//
//		log.info("findOne : {}", findOne);

//		mongoOps.dropCollection("person");
	}

}
