package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<String> postStudent(Student student) {
        repo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public ResponseEntity<String> updateStudent(int id, Student student) {
        Optional<Student> optionalStudent = repo.findById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id " + id);
        }

        Student existStudent = optionalStudent.get();
        existStudent.setStudentName(student.getStudentName());
        existStudent.setDepartment(student.getDepartment());
        existStudent.setDob(student.getDob());
        existStudent.setEmail(student.getEmail());
        existStudent.setNativePlace(student.getNativePlace());
        existStudent.setPhoneNo(student.getPhoneNo());

        repo.save(existStudent);

        return ResponseEntity.ok("Student updated successfully");
    }

    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}