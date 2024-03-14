package com.example.SpringBootTMS.work49;

import com.example.SpringBootTMS.work49.dto.StudentAdd;
import com.example.SpringBootTMS.work49.dto.StudentGroup;
import com.example.SpringBootTMS.work49.dto.StudentUpdate;
import com.example.SpringBootTMS.work49.model.CoursePayment;
import com.example.SpringBootTMS.work49.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UniversityService {
    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByGroup(String group) {
        return studentRepository.findByGroupOrderByLastName(group);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    public String updateStudent(StudentUpdate studentInformation) {
        Student student = studentRepository.findById(studentInformation.getId()).get();
        if (student != null) {
            student.setName(studentInformation.getName());
            student.setLastName(studentInformation.getLastname());
            CoursePayment coursePayment = student.getCoursePayment();
            if (coursePayment != null) {
                coursePayment.setIsPaid(studentInformation.getIsPaid());
                student.setCoursePayment(coursePayment);
            } else return "Student's payment not found";
            studentRepository.save(student);
            return "Successful";

        } else return "Student not found";
    }

    public List<Student> getStudentNoPayment() {
        return studentRepository.findByCoursePaymentIsPaidFalse();
    }

    public String delete(Long id) {
        studentRepository.deleteById(id);
        if (studentRepository.findById(id) == null)
            return "Successful";
        else return "Not deleted";
    }

    public String changeGroup(StudentGroup studentGroup) {
        Student student = studentRepository.findById(studentGroup.getId()).get();
        if (student != null) {
            student.setGroup(studentGroup.getGroup());
            studentRepository.save(student);
            return "Successful";
        } else return "Not successful";
    }

    public String addStudent(StudentAdd student) {
        Student stud = new Student();
        stud.setName(student.getName());
        stud.setLastName(student.getLastname());
        stud.setGroup(student.getGroup());
        CoursePayment coursePayment = new CoursePayment();
        coursePayment.setSum(student.getSum());
        coursePayment.setIsPaid(student.getIsPaid());
        coursePayment.setStudent(stud);
        stud.setCoursePayment(coursePayment);
         stud= studentRepository.save(stud);
        if (stud != null)
            return "Successful";
        else return "Not successful";
    }
}
