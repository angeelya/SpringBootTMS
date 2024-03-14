package com.example.SpringBootTMS.work49;

import com.example.SpringBootTMS.work49.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
     List<Student> findByGroupOrderByLastName(String group);
     List<Student> findAll();
     List<Student> findByName(String name);
     List<Student> findByLastName(String lastname);
     Optional<Student> findById(Long id);
     void deleteById(Long id);

     List<Student> findByCoursePaymentIsPaidFalse();
}
