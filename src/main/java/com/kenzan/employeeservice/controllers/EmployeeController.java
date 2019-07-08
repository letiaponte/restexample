package com.kenzan.employeeservice.controllers;

import com.kenzan.employeeservice.exceptions.EmployeeNotFoundException;
import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.repositories.EmployeeRepository;
import com.kenzan.employeeservice.services.EmployeeServiceImpl;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.DummyInvocationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;
    private final RootController assembler;

    EmployeeController(EmployeeServiceImpl employeeService,
                       RootController assembler) {

        this.employeeService = employeeService;
        this.assembler = assembler;
    }

    @GetMapping("/employees")
    public Resources<Resource<Employee>> findAll() {
        List<Resource<Employee>> employees = employeeService.findAll().stream()
                .map(employee -> new Resource<>(employee,
                        linkTo(DummyInvocationUtils.methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return new Resources<>(employees,
                linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel());
    }

    @GetMapping("/employees/{id}")
    public Resource<Employee> findById(@PathVariable Long id) {

        Employee employee = employeeService.findById(id);

        return assembler.toResource(employee);
    }

    @PostMapping("/employees")
    ResponseEntity<?> save(@RequestBody Employee newEmployee) throws URISyntaxException {
        Resource<Employee> resource = assembler.toResource(employeeService.save(newEmployee));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }



    @PutMapping("/employees/{id}")
    ResponseEntity<?> update (@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException {

        Employee updatedEmployee = employeeService.update(newEmployee, id);

        Resource<Employee> resource = assembler.toResource(updatedEmployee);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {

        boolean response = employeeService.delete(id);
        if (!response){
            throw new EmployeeNotFoundException(id);
        }

        return ResponseEntity.noContent().build();
    }


}
