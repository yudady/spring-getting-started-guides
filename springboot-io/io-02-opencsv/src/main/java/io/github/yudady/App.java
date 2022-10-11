package io.github.yudady;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// read
		Resource userCsv = resourceLoader.getResource("classpath:user.csv");
		CSVReader reader = new CSVReader(new FileReader(userCsv.getFile()));
		List<String[]> rows = reader.readAll();
		for (String[] row : rows) {
			System.out.println(row[0] + "," + row[1] + "," + row[2]);
		}

		// write
		CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("C:/Users/tommy.lin/Desktop/data.csv")));
		csvWriter.writeNext(new String[]{"John", "Doe", "jdoe@example.com", "Sales"});
		csvWriter.writeNext(new String[]{"Jane", "Doe", "jane.doe@example.com", "HR"});
		csvWriter.close();
	}

}
