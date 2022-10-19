package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//@Configuration
public class StudentConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
//        return args -> {
//            Student park_jiwon = new Student(
//                    "Park Jiwon",
//                    "jiwon@gmail.com",
//                    LocalDate.of(1999, Month.DECEMBER, 12)
//            );
//
//            Student saerom = new Student(
//                    "Lee Saerom",
//                    "saerom@gmail.com",
//                    LocalDate.of(2000, Month.JANUARY, 15)
//            );
//
//            studentRepository.saveAll(
//                    List.of(park_jiwon, saerom)
//            );
//        };
//    }
}
