package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class StudentController {

    private final StudentService studentservice;

    public StudentController(StudentService studentservice) {
        this.studentservice = studentservice;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        System.out.println("Received Student: " + student);
        return studentservice.postStudent(student);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents(@RequestParam(required = false) String sortBy, @RequestParam(required = false) Integer pageSize) {
        // Implement logic to use sortBy and pageSize to filter/sort the results
        System.out.println("Sort By: " + sortBy);
        System.out.println("Page Size: " + pageSize);
        return studentservice.getAllStudents(); // Modify this to use sortBy and pageSize
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = studentservice.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + id);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id,@RequestBody Student student) {
        return studentservice.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentservice.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
