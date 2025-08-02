package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Integer id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Integer id, @RequestBody Subject updatedSubject) {
        return subjectService.updateSubject(id, updatedSubject);
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return "Deleted subject with id: " + id;
    }
}
