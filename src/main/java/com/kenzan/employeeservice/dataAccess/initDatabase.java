package com.kenzan.employeeservice.dataAccess;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzan.employeeservice.models.Employee;
import com.kenzan.employeeservice.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        //Add registers preload to database
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        return args -> {
//            log.info("Preloading" + repository.save(new Employee("Maria", "G.", "Godinez", new Date(sdf.parse("1985-01-01").getTime()), new Date(sdf.parse("2019-01-01").getTime()), true)));
//            log.info("Preloading" + repository.save(new Employee("George", "", "Lopez", new Date(sdf.parse("1989-04-11").getTime()), new Date(sdf.parse("2019-05-09").getTime()), true)));
//        };

        //Read a JSON file and map that data to Employee model and save then into database
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/employees.json");
            try {
                List<Employee> employees = mapper.readValue(inputStream, typeReference);
                repository.save(employees);
                System.out.println("Employees Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save employee: " + e.getMessage());
            }
        };
    }
}
