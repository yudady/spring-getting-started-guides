package io02.export.opencsv.repository;

import org.springframework.data.repository.CrudRepository;

import io02.export.opencsv.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}