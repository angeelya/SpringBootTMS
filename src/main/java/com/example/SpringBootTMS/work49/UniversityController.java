package com.example.SpringBootTMS.work49;

import com.example.SpringBootTMS.work49.dto.Message;
import com.example.SpringBootTMS.work49.dto.StudentAdd;
import com.example.SpringBootTMS.work49.dto.StudentGroup;
import com.example.SpringBootTMS.work49.dto.StudentUpdate;
import com.example.SpringBootTMS.work49.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @GetMapping("/all")
    public ResponseEntity getStudents() {
        List<Student> students = universityService.getStudents();
        return checkListStudents(students);
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid StudentAdd student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrorMessage(bindingResult);
        } else return new Message(universityService.addStudent(student));
    }


    @GetMapping("/no/payment")
    public ResponseEntity getStudentsNoPayment() {
        List<Student> students = universityService.getStudentNoPayment();
        return checkListStudents(students);
    }

    @GetMapping("/by/name")
    public ResponseEntity getStudentsByName(@RequestParam("name") String name) {
        List<Student> students = universityService.getStudentsByName(name);
        return checkListStudents(students);
    }

    @GetMapping("/by/lastname")
    public ResponseEntity getStudentsByLastName(@RequestParam("lastname") String lastname) {
        List<Student> students = universityService.getStudentsByLastName(lastname);
        return checkListStudents(students);
    }
    @GetMapping("/by/group")
    public ResponseEntity getStudentsByGroup(@RequestParam("group") String group) {
        List<Student> students = universityService.getStudentsByGroup(group);
        return checkListStudents(students);
    }
    @PostMapping("/update")
    public Message update(@RequestBody @Valid StudentUpdate studentInformation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrorMessage(bindingResult);
        } else return new Message(universityService.updateStudent(studentInformation));
    }

    @PostMapping("/change/group")
    public Message change(@RequestBody @Valid StudentGroup studentGroup, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return getErrorMessage(bindingResult);
        }else
        return new Message(universityService.changeGroup(studentGroup));
    }

    private Message getErrorMessage(BindingResult bindingResult) {
        return new Message(bindingResult.getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining(". ")));
    }
    private ResponseEntity checkListStudents(List<Student> students) {
        if (students.size()!=0)
            return new ResponseEntity(students, HttpStatus.OK);
        else return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
    }
}
