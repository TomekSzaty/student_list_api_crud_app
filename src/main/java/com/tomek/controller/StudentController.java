package com.tomek.controller;

import com.tomek.service.StudentService;
import com.tomek.student.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent() {
        return studentService.getStudent();
    }
    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }
    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer id) {
        studentService.deleteStudent(id);
    }

}
