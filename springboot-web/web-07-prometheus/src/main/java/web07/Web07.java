package web07;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Web07 {

	public static void main(String[] args) {
		SpringApplication.run(Web07.class, args);
	}


	/**
	 * https://www.cnblogs.com/cjsblog/p/11556029.html
	 */
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", Web07.class.getName());
	}

}
