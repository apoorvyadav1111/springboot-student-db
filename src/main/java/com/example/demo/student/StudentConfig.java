package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john = new Student(
                    "John",
                    "jd@hotmail.com",
                    LocalDate.of(1996, Month.JUNE, 10)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@hotmail.con",
                    LocalDate.of(2004, Month.DECEMBER, 26)
            );

            repository.saveAll(
                    List.of(john, alex)
            );
        };
    }
}
