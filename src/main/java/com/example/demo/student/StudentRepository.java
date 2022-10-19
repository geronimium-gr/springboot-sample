package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//  Option 1: by using List
//    @Query(value = "SELECT u FROM Student u WHERE u.email LIKE %:studentModel%")
//    List<Student> emailExisting(@Param("studentModel") String email);


//   Option 2: by using Optional
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentEmail(String email);

//    @Modifying
//    @Query("update Customer u set u.phone = :phone where u.id = :id")
//    void updatePhone(@Param(value = "id") long id, @Param(value = "phone") String phone);

}
