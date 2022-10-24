package io03.export.pdf;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io03.export.pdf.model.Employee;
import io03.export.pdf.repository.EmployeeRepository;

@SpringBootApplication
public class Io03 implements CommandLineRunner {

	@Autowired
	EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Io03.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (repository.count() == 0) {
			// save a list of Employees
			repository.saveAll(Arrays.asList(new Employee("Adam", "John"), new Employee("Sibin", "Rasiya"),
					new Employee("Arun", "Mohan"), new Employee("Scott", "Morrison"),
					new Employee("Hikaru", "Nakamura"), new Employee("Ishivaka", "Yusuke")));
		}

	}
}
