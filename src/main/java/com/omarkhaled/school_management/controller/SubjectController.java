package com.omarkhaled.school_management.controller;

import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.repository.SubjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }
    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Integer id){
        return subjectRepository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "subject not found"));
    }

    @PostMapping
    public Subject addSubject(@Valid @RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Integer id){
        subjectRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Integer id, @RequestBody Subject updatedSubject){
        Subject subject = subjectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "subject not found")
        );
        if (updatedSubject.getName() != null) {
            subject.setName(updatedSubject.getName());
        }
        if (updatedSubject.getDescription() != null) {
            subject.setDescription(updatedSubject.getDescription());
        }
        return subjectRepository.save(subject);
    }

}
