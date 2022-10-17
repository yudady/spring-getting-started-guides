package yudady.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.exporter.PushGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "hello world";
	}

	/**
	 * https://www.cnblogs.com/cjsblog/p/11556029.html
	 */
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", DemoApplication.class.getName());
	}

	/**
	 * push网关
	 *
	 * @return
	 */
	@Bean
	public PushGateway getPushGateway() {
		return new PushGateway("localhost:9090");
	}
}
