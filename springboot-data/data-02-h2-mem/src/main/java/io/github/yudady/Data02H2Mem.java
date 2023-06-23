package io.github.yudady;


import io.github.yudady.model.Employee;
import io.github.yudady.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Data02H2Mem implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
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

    public static void main(String[] args) {
        SpringApplication.run(Data02H2Mem.class, args);
    }
}
