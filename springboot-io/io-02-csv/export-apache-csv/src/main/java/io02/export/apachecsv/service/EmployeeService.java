package io02.export.apachecsv.service;

import io02.export.apachecsv.helper.CSVHelper;
import io02.export.apachecsv.model.Employee;
import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io02.export.apachecsv.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> fetchAll() {
		return (List<Employee>) employeeRepository.findAll();

	}

	public ByteArrayInputStream load() {
		List<Employee> tutorials = fetchAll();

		ByteArrayInputStream in = CSVHelper.employeesToCSV(tutorials);
		return in;
	}
}
