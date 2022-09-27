package com.example.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class BatchProcessingApplication {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext applicationContext = (AbstractApplicationContext) SpringApplication.run(BatchProcessingApplication.class, args);
		applicationContext.getBeanFactory()
			.getBeanNamesIterator()
			.forEachRemaining(beanName -> {
				Object bean = applicationContext.getBean(beanName);
				System.out.println(beanName + " : " + bean.getClass().getName());
			});

		System.exit(SpringApplication.exit(applicationContext));
	}
}
