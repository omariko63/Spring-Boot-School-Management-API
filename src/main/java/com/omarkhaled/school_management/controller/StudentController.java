package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.model.Student;
import com.omarkhaled.school_management.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student){
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Deleted student with id: " + id;
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent){
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if(updatedStudent.getName() != null){
            student.setName(updatedStudent.getName());
        }
        if(updatedStudent.getEmail() != null){
            student.setEmail(updatedStudent.getEmail());
        }
        if(updatedStudent.getGrade()!= null){
            student.setGrade(updatedStudent.getGrade());
        }
        return studentRepository.save(student);
    }


}
