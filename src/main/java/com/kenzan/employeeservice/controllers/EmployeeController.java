package com.kenzan.employeeservice.controllers;

import com.kenzan.employeeservice.Errors.EmployeeNotFoundException;
import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.repositories.EmployeeRepositoryH2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
class EmployeeController {
    private final EmployeeRepositoryH2 repositoryH2;

    EmployeeController(EmployeeRepositoryH2 repositoryH2) {
        this.repositoryH2=repositoryH2;
    }

    //Agregate root

    @GetMapping("/employees")
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        Iterable<Employee> all = repositoryH2.findAll();
        for (Employee employee:all){
            employees.add(employee);
        }
        return employees;
    }

    @PostMapping("/employees")
    Employee newEmployee (@RequestBody Employee newEmployee){
        return repositoryH2.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee findById(@PathVariable Long id){
        return repositoryH2.findOne(id);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        return repositoryH2.save(newEmployee);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee (@PathVariable Long id) {
        repositoryH2.delete(id);
    }

}
