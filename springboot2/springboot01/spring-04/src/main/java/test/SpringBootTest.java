package test;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.yudady.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

@EnableAutoConfiguration
@Configuration
@PropertySource(value = { "classpath:/data.json" }, factory = SpringBootTest.JsonLoader.class)
public class SpringBootTest extends SpringBootServletInitializer {

	@Bean
	public Object test(Environment e) {
		System.out.println(e.getProperty("my"));
		return new Object();
	}


	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(SpringBootTest.class, args)));
	}

	public static class JsonLoader implements PropertySourceFactory {

		@Override
		public org.springframework.core.env.PropertySource<?> createPropertySource(String name,
				EncodedResource resource) throws IOException {
			Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
			return new MapPropertySource("json-source", readValue);
		}

	}
}
