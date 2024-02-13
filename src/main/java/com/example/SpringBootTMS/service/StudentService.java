package com.example.SpringBootTMS.service;

import com.example.SpringBootTMS.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
        students.add(new Student("Elizabeth", 20, "Math"));
        students.add(new Student("Elena", 21, "Biology"));
        students.add(new Student("Olga", 18, "Economy"));
        students.add(new Student("Mark", 19, "Programming"));
        LOGGER.info("A list of students has been created: "+students);
    }

    public void addStudent(Student student) {
        students.add(student);
        LOGGER.info("Student added to the list"+student);

    }
    public void deleteStudent(String name)
    {
     students=students.stream().filter(student -> !student.getName().equals(name)).collect(Collectors.toList());
        LOGGER.info("Students "+name+" deleted to the list");

    }
    public List<Student> getAllStudents()
    {
        return students;
    }
    public List<Student> getStudentsBySpeciality(String speciality)
    {
        return students.stream().filter(student -> student.getSpeciality().equals(speciality)).collect(Collectors.toList());
    }
}
