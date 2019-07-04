package com.kenzan.employeeservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Employee {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String middleInitial;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEmployment;
    private boolean status;

    Employee() {
    }

    public Employee(String firstName, String middleInitial, String lastName, Date dateOfBirth, Date dateOfEmployment, boolean status) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.status = status;
    }
}
