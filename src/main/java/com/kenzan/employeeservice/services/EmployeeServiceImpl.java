package com.kenzan.employeeservice.services;

import com.kenzan.employeeservice.exceptions.EmployeeNotFoundException;
import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;

    EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;

    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        Iterable<Employee> all = employeeRepository.findByStatus(true);
        for (Employee employee : all) {
            employees.add(employee);
        }

        return employees;
    }

    public Employee findById(Long id) {

        Employee employee = employeeRepository.findOne(id);
        if (employee == null || !employee.isStatus()) {
            throw new EmployeeNotFoundException(id);
        }

        return employee;
    }

    public Employee save(Employee employee) {

        return employeeRepository.save(employee);
    }


    public Employee update(Employee newEmployee, Long id) {

        Employee updatedEmployee = employeeRepository.findOne(id);
        employeeRepository.save(newEmployee);

        if (updatedEmployee == null || !updatedEmployee.isStatus()) {
            throw new EmployeeNotFoundException(id);
        } else {
            employeeRepository.save(updatedEmployee);
        }

        return updatedEmployee;
    }

    public boolean delete(Long id) {

        Employee employee = employeeRepository.findOne(id);
        if (employee == null || !employee.isStatus()) {
            throw new EmployeeNotFoundException(id);
        } else {
            employee.setStatus(false);
            employeeRepository.save(employee);
            return true;
        }

    }

}
