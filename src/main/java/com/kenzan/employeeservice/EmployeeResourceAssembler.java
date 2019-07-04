package com.kenzan.employeeservice;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.kenzan.employeeservice.controllers.EmployeeController;
import com.kenzan.employeeservice.models.Employee;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource <Employee>> {

    @Override
    public Resource<Employee> toResource (Employee employee) {

        return  new Resource<>(employee,
            linkTo (methodOn (EmployeeController.class).findById (employee.getId())).withSelfRel(),
            linkTo (methodOn (EmployeeController.class).findAll()).withRel("employees") );
    }
}
