package web02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableCaching
@Slf4j
public class Web02 implements ApplicationRunner, CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	CacheManager cacheManager;

	@Autowired
	ConcurrentMapCacheManager concurrentMapCacheManager;

	public static void main(String[] args) {
		SpringApplication.run(Web02.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			if (beanDefinitionName.contains("cache")) {
				System.out.println(beanDefinitionName + " : " + applicationContext.getBean(beanDefinitionName).getClass());
			}
		}
	}


	@Override
	public void run(String... args) throws Exception {
		// org.springframework.cache.concurrent.ConcurrentMapCacheManager
		log.info(cacheManager.getClass().getName());
		log.info(concurrentMapCacheManager.getClass().getName());
		System.out.println(cacheManager == concurrentMapCacheManager);
	}
}
