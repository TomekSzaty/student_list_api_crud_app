package com.tomek.config;

import com.tomek.repository.StudentRepository;
import com.tomek.student.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student alex = new Student(
                    "Alex",
                    "alexm@gmail.com",
                    LocalDate.of(2000, FEBRUARY, 24)
            );
            Student miriam = new Student(
                    "Miriam",
                    "miriam@gmail.com",
                    LocalDate.of(2002, MARCH, 14)
            );
            repository.saveAll(
                    List.of(alex, miriam)
            );
        };
    }
}
