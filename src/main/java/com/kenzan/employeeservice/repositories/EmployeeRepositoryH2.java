package com.kenzan.employeeservice.repositories;

import com.kenzan.employeeservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryH2 extends JpaRepository<Employee,Long> {
}
