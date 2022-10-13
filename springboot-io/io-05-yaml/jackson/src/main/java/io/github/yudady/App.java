package io.github.yudady;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.yudady.model.Order;
import io.github.yudady.model.OrderLine;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner, ApplicationRunner {

	@Autowired
	ApplicationContext context;


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Resource resource = context.getResource("orderInput.yaml");
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();
		Order order = mapper.readValue(resource.getFile(), Order.class);
		System.out.println("ApplicationRunner done " + order);
	}

	@Override
	public void run(String... args) throws Exception {

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory()
			.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));

		//		Java 8 date/time type `java.time.LocalDate` not supported by default:
		//		add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
		//		to enable handling (through reference chain: io.github.yudady.model.Order["date"])
		mapper.registerModule(new JavaTimeModule());

		List<OrderLine> lines = new ArrayList<>();
		lines.add(new OrderLine("Copper Wire (200ft)", 1, new BigDecimal(50.67).setScale(2, RoundingMode.HALF_UP)));
		lines.add(new OrderLine("Washers (1/4)", 24, new BigDecimal(.15).setScale(2, RoundingMode.HALF_UP)));


		Order order = new Order(
			"B-9910",
			LocalDate.parse("2019-04-18", DateTimeFormatter.ISO_DATE),
			"Customer, Jane",
			lines);


		mapper.writeValue(new File("C:/Users/tommy.lin/Desktop/orderOutput.yaml"), order);

		System.out.println("CommandLineRunner done " + order);
	}
}
