package data05;

import com.mongodb.client.MongoClients;
import data05.mode.Person;
import data05.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@SpringBootApplication
@EnableMongoRepositories
public class Data05 implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Data05.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			if (!beanDefinitionName.contains("ongo")) continue;
			Object bean = applicationContext.getBean(beanDefinitionName);
			System.out.println(beanDefinitionName + " : " + bean);
		}



	}
}
