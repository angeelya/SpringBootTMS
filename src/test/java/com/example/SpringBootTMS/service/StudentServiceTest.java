package com.example.SpringBootTMS.service;

import com.example.SpringBootTMS.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceTest {
    @Autowired
    StudentService service;
    List<Student> studentList;

    @Test
    void addStudent() {
        Student student = new Student("Elena",23,"Art");
        service.addStudent(student);
       studentList=service.getAllStudents();
        assertTrue(studentList.contains(student));
    }

    @Test
    void deleteStudent() {
        String name ="Elena";
        service.deleteStudent(name);
        studentList=service.getAllStudents();
        assertTrue(studentList.stream().noneMatch(student -> student.getName().equals(name)));
    }


    @Test
    void getStudentsBySpeciality() {
        String speciality="Economy";
        studentList =service.getStudentsBySpeciality(speciality);
        assertTrue(studentList.stream().allMatch(student -> student.getSpeciality().equals(speciality)));
    }
}