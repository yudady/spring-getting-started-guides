package data05;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class Test02_SpringMongoClient {

	//	@Autowired
//	MongoTemplate mongoOps;
	@Autowired
	MongoClient mongoClient;

	@Test
	void test() {
		MongoIterable<String> strings = mongoClient.listDatabaseNames();
		for (String mongo : strings) {
			System.out.println("mongo = " + mongo);
		}

	}

}
