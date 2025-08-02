package com.omarkhaled.school_management.service;

import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Integer id, Subject updatedSubject) {
        Subject subject = getSubjectById(id);

        if (updatedSubject.getName() != null) subject.setName(updatedSubject.getName());
        if (updatedSubject.getDescription() != null) subject.setDescription(updatedSubject.getDescription());
        if (updatedSubject.getCapacity() != null) subject.setCapacity(updatedSubject.getCapacity());

        return subjectRepository.save(subject);
    }

    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}
