package io.github.yudady.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.yudady.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}