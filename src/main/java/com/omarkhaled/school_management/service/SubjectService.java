package com.omarkhaled.school_management.service;

import com.omarkhaled.school_management.dto.SubjectDTO;
import com.omarkhaled.school_management.mapper.SubjectMapper;
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

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectMapper::toDTO)
                .toList();
    }

    public SubjectDTO getSubjectById(Integer id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
        return SubjectMapper.toDTO(subject);
    }

    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = SubjectMapper.toEntity(subjectDTO);
        return SubjectMapper.toDTO(subjectRepository.save(subject));
    }

    public SubjectDTO updateSubject(Integer id, SubjectDTO updatedSubject) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));

        if (updatedSubject.name() != null) subject.setName(updatedSubject.name());
        if (updatedSubject.description() != null) subject.setDescription(updatedSubject.description());

        return SubjectMapper.toDTO(subjectRepository.save(subject));
    }

    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}
