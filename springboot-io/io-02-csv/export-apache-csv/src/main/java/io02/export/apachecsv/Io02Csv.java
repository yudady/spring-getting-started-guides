package io02.export.apachecsv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io02.export.apachecsv.model.Employee;
import io02.export.apachecsv.repository.EmployeeRepository;

@SpringBootApplication
public class Io02Csv implements CommandLineRunner {
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Io02Csv.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = new ArrayList<>();

		// create dummy employees
		employees.add(new Employee("Dummy-1", "dummy1@example.com", "India", 35, "Lead Tester"));
		employees.add(new Employee("Dummy-2", "dummy2@srovoki.me", "USA", 25, "Tester"));
		employees.add(new Employee("Dummy-3", "dummy3@gmail.com", "Japan", 29, "Sr.Tester"));
		employees.add(new Employee("Dummy-1", "dummy1@example.com", "India", 35, "Lead Tester"));
		employees.add(new Employee("Dummy-2", "dummy2@srovoki.me", "USA", 25, "Tester"));
		employees.add(new Employee("Dummy-3", "dummy3@gmail.com", "Japan", 29, "Sr.Tester"));
		employees.add(new Employee("Dummy-1", "dummy1@example.com", "India", 35, "Lead Tester"));
		employees.add(new Employee("Dummy-2", "dummy2@srovoki.me", "USA", 25, "Tester"));
		employees.add(new Employee("Dummy-3", "dummy3@gmail.com", "Japan", 29, "Sr.Tester"));
		employees.add(new Employee("Dummy-1", "dummy1@example.com", "India", 35, "Lead Tester"));
		employees.add(new Employee("Dummy-2", "dummy2@srovoki.me", "USA", 25, "Tester"));
		employees.add(new Employee("Dummy-3", "dummy3@gmail.com", "Japan", 29, "Sr.Tester"));
		employees.add(new Employee("Dummy-1", "dummy1@example.com", "India", 35, "Lead Tester"));
		employees.add(new Employee("Dummy-2", "dummy2@srovoki.me", "USA", 25, "Tester"));
		employees.add(new Employee("Dummy-3", "dummy3@gmail.com", "Japan", 29, "Sr.Tester"));
		employeeRepository.saveAll(employees);
	}

}
