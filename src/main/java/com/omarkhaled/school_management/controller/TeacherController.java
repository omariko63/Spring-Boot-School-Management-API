package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.model.Teacher;
import com.omarkhaled.school_management.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Integer id) {
        return teacherRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }
    @PostMapping
    public Teacher addTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Integer id) {
        teacherRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Teacher updateTeacher( @PathVariable Integer id ,@RequestBody Teacher updatedTeacher) {
        Teacher teacher = teacherRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
        if(updatedTeacher.getName()!=null){
            teacher.setName(updatedTeacher.getName());
        }
        if(updatedTeacher.getEmail()!=null){
            teacher.setEmail(updatedTeacher.getEmail());
        }
        return teacherRepository.save(teacher);
    }

}
