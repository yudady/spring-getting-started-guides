package data05;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class Test01_MongoClients {


	@Test
	void test() {
		MongoClient mongoClient = MongoClients.create("mongodb://test:test@localhost:27017/test");

		MongoIterable<String> strings = mongoClient.listDatabaseNames();
		for (String mongo : strings) {
			System.out.println("mongo = " + mongo);
		}

	}

}
