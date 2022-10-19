package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final AtomicLong counter = new AtomicLong();
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

//    @DeleteMapping
//    public void removeStudent(@RequestBody Student student ) {
//        studentService.deleteStudent(student);
//    }

    @DeleteMapping(path = "/remove/{studentId}")
    public ResponseEntity removeStudent(@PathVariable("studentId") Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/update/{studentId}", consumes = {"application/xml", "application/json", "text/plain"})
    public ResponseEntity updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student){
       return studentService.updateStudent(studentId, student);
    }

}
