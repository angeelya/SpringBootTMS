package com.example.SpringBootTMS.work41;

import com.example.SpringBootTMS.work39.model.UsersApp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class StudentController {
    @Autowired
    StudentService service;
    @GetMapping("/all")
    public String getStudent(Model model) {
     model.addAttribute("students", service.getAllStudents());
     return "studentsList";
    }
    @GetMapping("/allBySpeciality/{speciality}")
    public String getStudentBySpeciality(@PathVariable("speciality")String speciality,Model model) {
        model.addAttribute("students", service.getStudentsBySpeciality(speciality));
        return "studentsList";
    }
    @GetMapping(value = "/insert")
    public String student(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }
    @PostMapping("/addStudent")
    public String insert(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult,
                         Model model) {
        if(bindingResult.hasErrors())
            return "addStudent";
        service.addStudent(student);
        model.addAttribute("name",student.getName());
        model.addAttribute("age",student.getAge());
        model.addAttribute("speciality",student.getSpeciality());
        return "student";
    }
    @GetMapping("/delete_student/{name}")
    public String deleteStudents(@PathVariable("name") String name,Model model) {
        service.deleteStudent(name);
        model.addAttribute("message","Delete is successful. Student "+name+" deleted");
        return "result";
    }

}
