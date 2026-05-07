package com.example.loginapp.controller;

import com.example.loginapp.model.Student;
import com.example.loginapp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {

        return studentRepository.save(student);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "Student Deleted";
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student updatedStudent){

        Student student = studentRepository.findById(id).orElse(null);

        if(student != null){

            student.setName(updatedStudent.getName());

            student.setCourse(updatedStudent.getCourse());

            student.setEmail(updatedStudent.getEmail());

            return studentRepository.save(student);
        }

        return null;
    }
}