package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.dto.SubjectDTO;
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
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectDTO getSubject(@PathVariable Integer id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public SubjectDTO addSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.createSubject(subjectDTO);
    }

    @PutMapping("/{id}")
    public SubjectDTO updateSubject(@PathVariable Integer id, @RequestBody SubjectDTO updatedSubject) {
        return subjectService.updateSubject(id, updatedSubject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
    }
}
