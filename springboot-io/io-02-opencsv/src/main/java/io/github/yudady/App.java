package io.github.yudady;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import io.github.yudady.model.User;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


/**
 * https://opencsv.sourceforge.net/#quick_start
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		readCSVFileToBean();
		readCSVFile();
		readCSVFileUsingCSVReaderBuilder();
		writeCSVFile();
	}

	private void readCSVFileToBean() throws IOException {
		Resource userCsv = resourceLoader.getResource("classpath:user.csv");
		FileReader reader = new FileReader(userCsv.getFile());
		Objects.requireNonNull(reader);
		List<User> beans = new CsvToBeanBuilder<User>(reader)
			.withType(User.class)
			.withSeparator(',')
			.withSkipLines(1)
			.build()
			.parse();

		beans.forEach(System.out::println);
	}

	private void readCSVFileUsingCSVReaderBuilder() throws IOException, CsvException {
		Resource userCsv = resourceLoader.getResource("classpath:user.csv");
		CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // custom separator
		try (CSVReader reader = new CSVReaderBuilder(new FileReader(userCsv.getFile()))
			.withCSVParser(csvParser)   // custom CSV parser
			.withSkipLines(1)           // skip the first line, header info
			.build()
		) {
			reader.readAll().forEach(row -> System.out.println(row[0] + "," + row[1] + "," + row[2]));

		}

	}

	private void readCSVFile() throws IOException, CsvException {
		// read
		Resource userCsv = resourceLoader.getResource("classpath:user.csv");
		CSVReader reader = new CSVReader(new FileReader(userCsv.getFile()));
		reader.skip(1); // 忽略title


		//
		reader.readAll().forEach(row -> System.out.println(row[0] + "," + row[1] + "," + row[2]));
	}

	private void writeCSVFile() throws IOException {
		// write
		CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("C:/Users/tommy.lin/Desktop/data.csv")));
		csvWriter.writeNext(new String[]{"John", "Doe", "jdoe@example.com", "Sales"});
		csvWriter.writeNext(new String[]{"Jane", "Doe", "jane.doe@example.com", "HR"});
		csvWriter.close();
	}
}
