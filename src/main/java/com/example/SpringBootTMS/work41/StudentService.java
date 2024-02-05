package com.example.SpringBootTMS.work41;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
        students.add(new Student("Elizabeth", 20, "Math"));
        students.add(new Student("Elena", 21, "Biology"));
        students.add(new Student("Olga", 18, "Economy"));
        students.add(new Student("Mark", 19, "Programming"));
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public void deleteStudent(String name)
    {
     students=students.stream().filter(student -> !student.getName().equals(name)).collect(Collectors.toList());
    }
    public List<Student> getAllStudents()
    {
        return students;
    }
    public List<Student> getStudentsBySpeciality(String speciality)
    {
        return students.stream().filter(student -> student.getSpeciality().equals(speciality)).toList();
    }
}
