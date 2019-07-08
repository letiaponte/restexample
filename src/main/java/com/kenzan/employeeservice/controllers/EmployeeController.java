package com.kenzan.employeeservice.controllers;

import com.kenzan.employeeservice.exceptions.EmployeeNotFoundException;
import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.services.EmployeeServiceImpl;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Service for employees management
 */
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;
    private final RootController rootController;

    EmployeeController(EmployeeServiceImpl employeeService,
                       RootController assembler) {

        this.employeeService = employeeService;
        this.rootController = assembler;
    }

    @GetMapping("/")
    public Resources<Resource<Employee>> findAll() {
        List<Resource<Employee>> employees = new ArrayList<>();
        Iterable <Employee> all = employeeService.findAll();
        for (Employee employee:all){
            employees.add(rootController.toResource(employee));
        }

        return new Resources<>(employees,
                linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Employee> findById(@PathVariable Long id) {

        Employee employee = employeeService.findById(id);

        return rootController.toResource(employee);
    }

    @PostMapping("/")
    ResponseEntity<?> save(@RequestBody Employee newEmployee) throws URISyntaxException {
        Resource<Employee> resource = rootController.toResource(employeeService.save(newEmployee));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }



    @PutMapping("/{id}")
    ResponseEntity<?> update (@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException {

        Employee updatedEmployee = employeeService.update(newEmployee, id);

        Resource<Employee> resource = rootController.toResource(updatedEmployee);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {

        boolean response = employeeService.delete(id);
        if (!response){
            throw new EmployeeNotFoundException(id);
        }

        return ResponseEntity.noContent().build();
    }


}
