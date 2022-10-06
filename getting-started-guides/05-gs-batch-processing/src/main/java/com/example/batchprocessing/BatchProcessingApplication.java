package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class BatchProcessingApplication {

	private static final Logger log = LoggerFactory.getLogger(BatchProcessingApplication.class);

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext applicationContext = (AbstractApplicationContext) SpringApplication.run(BatchProcessingApplication.class, args);
		applicationContext.getBeanFactory()
			.getBeanNamesIterator()
			.forEachRemaining(beanName -> {
				Object bean = applicationContext.getBean(beanName);
				log.warn(beanName + " : " + bean.getClass().getName());
			});

		System.exit(SpringApplication.exit(applicationContext));
	}
}
