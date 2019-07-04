package com.kenzan.employeeservice.repositories;

import com.kenzan.employeeservice.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepositoryH2 extends CrudRepository<Employee, Long> {
}
