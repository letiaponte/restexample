package com.kenzan.employeeservice.controllers;

import com.kenzan.employeeservice.Errors.EmployeeNotFoundException;
import com.kenzan.employeeservice.EmployeeResourceAssembler;
import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.repositories.EmployeeRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

    private final EmployeeRepository repository;
    private final EmployeeResourceAssembler assembler;

    EmployeeController(EmployeeRepository repository,
                       EmployeeResourceAssembler assembler) {

        this.repository =repository;
        this.assembler=assembler;
    }

    //Agregate root

    @GetMapping("/employees")
    public Resources<Resource<Employee>> findAll() {
        List<Resource<Employee>> employees = new ArrayList<>();
        Iterable <Employee> all = repository.findAll();
        for (Employee employee:all){
            employees.add(assembler.toResource(employee));
        }

        return new Resources<>(employees,
                linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel());
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee (@RequestBody Employee newEmployee) throws URISyntaxException {
        Resource<Employee> resource = assembler.toResource(repository.save(newEmployee));

        return  ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/employees/{id}")
    public Resource<Employee> findById(@PathVariable Long id){

        Employee employee = repository.findOne(id);
         if (employee == null) {
             new EmployeeNotFoundException(id);
         }

         return assembler.toResource(employee);
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee (@RequestBody Employee newEmployee, @PathVariable Long id) throws  URISyntaxException {
        Employee updatedEmployee = repository.save(newEmployee);

        Resource<Employee> resource = assembler.toResource(updatedEmployee);

        return ResponseEntity
            .created(new URI(resource.getId().expand().getHref()))
            .body(resource);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee (@PathVariable Long id) {

        repository.delete(id);

        return ResponseEntity.noContent().build();
    }


}
