package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.model.Teacher;
import com.omarkhaled.school_management.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Integer id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Integer id, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(id, updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return "Deleted teacher with id: " + id;
    }
}
