package com.kenzan.employeeservice.controllers;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.kenzan.employeeservice.models.Employee;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping(value = "/")
public class RootController implements ResourceAssembler<Employee, Resource <Employee>> {

    @GetMapping("/")
    ResponseEntity<ResourceSupport> index() {
        ResourceSupport resourceSupport = new ResourceSupport();
        resourceSupport.add(linkTo(methodOn(RootController.class).index()).withSelfRel());
        resourceSupport.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));
        return ResponseEntity.ok(resourceSupport);
    }

    @Override
    public Resource<Employee> toResource (Employee employee) {

        return  new Resource<>(employee,
            linkTo (methodOn (EmployeeController.class).findById (employee.getId())).withSelfRel(),
            linkTo (methodOn (EmployeeController.class).findAll()).withRel("employees") );
    }
}
