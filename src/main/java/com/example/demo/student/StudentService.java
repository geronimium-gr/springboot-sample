package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(RuntimeException::new);
    }


//    public void addStudent(Student student) {
////      Option 1: Use List
////        List<Student> results = studentRepository.emailExisting(student.getEmail());
//
//        Optional<Student> studentEmail = studentRepository.findStudentEmail(student.getEmail());
//
//        if (studentEmail.isPresent()){
//            System.out.println("Email already exists.");
//        } else {
//            studentRepository.save(student);
//        }
//    }

    public ResponseEntity<Object> addStudent(Student student) {
//      Option 1: Use List
//        List<Student> results = studentRepository.emailExisting(student.getEmail());

        Optional<Student> studentEmail = studentRepository.findStudentEmail(student.getEmail());

        if (studentEmail.isPresent()){
            return new ResponseEntity("Email already exists.", HttpStatus.BAD_REQUEST);
        }

        studentRepository.save(student);
        //return new ResponseEntity("New Student added.", HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "name", student.getName(),
                "email", student.getEmail(),
                "birthday", student.getBirthday()
        ));

    }

    //Option 1
//    public void deleteStudent(Long id) {
//        Student student = studentRepository.findById(id).orElseThrow(() ->
//                new InvalidConfigurationPropertyValueException("Student not found", id, "Student Not Existing"));
//
//        studentRepository.delete(student);
//    }

    //Option 2 - Error message if ID doesn't exist.
//    public void deleteStudent(Long studentId) {
//        boolean exists = studentRepository.existsById(studentId);
//
//        if (!exists) {
//            throw new IllegalStateException("This ID: " + studentId + " does not exists.");
//        }
//
//        studentRepository.deleteById(studentId);
//    }

    //Option 3: With HTTP Response
    public ResponseEntity deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            return new ResponseEntity("ID: " + studentId + " not exists.", HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(studentId);
        return new ResponseEntity("Student Deleted.", HttpStatus.OK);
    }

    public ResponseEntity updateStudent(Long studentId, Student student) {
        Student currentStudent = studentRepository.findById(studentId).orElseThrow(RuntimeException::new);

        Optional<Student> studentEmail = studentRepository.findStudentEmail(student.getEmail());

        System.out.println(student.getEmail());
        System.out.println(currentStudent.getEmail());
        if (studentEmail.isPresent() && !Objects.equals(student.getEmail(), currentStudent.getEmail())){
            return new ResponseEntity("Email already exists.", HttpStatus.BAD_REQUEST);
        }

        currentStudent.setName(student.getName() == null ? currentStudent.getName() : student.getName());
//        if (student.getEmail() == currentStudent.getEmail()){
//            currentStudent.setEmail(currentStudent.getEmail());
//        } else {
//        }
        currentStudent.setEmail(student.getEmail() == null ? currentStudent.getEmail() : student.getEmail());

        currentStudent.setBirthday(student.getBirthday() == null ? currentStudent.getBirthday() : student.getBirthday());


        studentRepository.save(currentStudent);

        return ResponseEntity.ok(currentStudent);

    }
}
