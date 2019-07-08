package com.kenzan.employeeservice.repositories;

import com.kenzan.employeeservice.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable<Employee> findByStatus(boolean b);
}
