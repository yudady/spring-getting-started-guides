package io02.export.apachecsv.repository;

import io02.export.apachecsv.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}