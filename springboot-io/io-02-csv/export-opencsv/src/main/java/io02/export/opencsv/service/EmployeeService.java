package io02.export.opencsv.service;

import io02.export.opencsv.model.Employee;
import io02.export.opencsv.repository.EmployeeRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> fetchAll() {
		return (List<Employee>) employeeRepository.findAll();

	}

}
