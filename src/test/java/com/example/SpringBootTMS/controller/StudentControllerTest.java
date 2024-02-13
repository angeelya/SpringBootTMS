package com.example.SpringBootTMS.controller;

import com.example.SpringBootTMS.model.Student;
import com.example.SpringBootTMS.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;


    @Test
    void getStudent() throws Exception {
        when(studentService.getAllStudents()).thenReturn(getStudents());
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("students"))
                .andExpect(view().name("studentsList"));
        verify(studentService, times(1)).getAllStudents();
    }

    private List<Student> getStudents() {
        return new ArrayList<>(Arrays.asList(
                new Student("Irina", 20, "Computer Science"),
                new Student("Mark", 22, "Computer Science"),
                new Student("Evgenia", 20, "Art")));
    }

    @Test
    void getStudentBySpeciality() throws Exception {
        String speciality = "Computer Science";
        when(studentService.getStudentsBySpeciality(speciality)).thenReturn(getStudents()
                .stream().filter(student -> student.getSpeciality().equals(speciality))
                .collect(Collectors.toList()));
        mockMvc.perform(get("/allBySpeciality/{speciality}", speciality))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("students"))
                .andExpect(view().name("studentsList"));
        verify(studentService, times(1)).getStudentsBySpeciality(speciality);
    }

    @Test
    void student() throws Exception {
        mockMvc.perform(get("/insert"))
                .andExpect(model().attributeExists("student"))
                .andExpect(view().name("addStudent"));
    }

    @Test
    void insert() throws Exception {
        Student student = new Student("Danila",21,"Math");
        mockMvc.perform(post("/addStudent")
                        .param("name",student.getName())
                        .param("age", String.valueOf(student.getAge()))
                        .param("speciality", student.getSpeciality()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("age"))
                .andExpect(model().attributeExists("speciality"))
                .andExpect(view().name("student"));
        verify(studentService).addStudent(student);
    }

    @Test
    void deleteStudents() throws Exception {
        String name = "Irina";
        mockMvc.perform(get("/delete_student/{name}", name))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("result"));
        verify(studentService).deleteStudent(name);
    }
}