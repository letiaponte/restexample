package com.kenzan.employeeservice;


import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.repositories.EmployeeRepositoryH2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@Slf4j
 class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepositoryH2 repository) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return args -> {
            log.info("Preloading" + repository.save(new Employee("Maria", "G.", "Godinez", new Date(sdf.parse("1985-01-01").getTime()), new Date(sdf.parse("2019-01-01").getTime()), true)));
            log.info("Preloading" + repository.save(new Employee("George", "", "Lopez", new Date(sdf.parse("1989-04-11").getTime()), new Date(sdf.parse("2019-05-09").getTime()), true)));
        };
    }
}
