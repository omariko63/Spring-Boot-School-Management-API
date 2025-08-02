package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.dto.TeacherDTO;
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
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDTO getTeacher(@PathVariable Integer id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.createTeacher(teacherDTO);
    }

    @PutMapping("/{id}")
    public TeacherDTO updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO updatedTeacher) {
        return teacherService.updateTeacher(id, updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
    }
}
