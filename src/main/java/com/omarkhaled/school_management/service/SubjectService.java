package com.omarkhaled.school_management.service;

import com.omarkhaled.school_management.dto.SubjectDTO;
import com.omarkhaled.school_management.mapper.SubjectMapper;
import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.repository.StudentRepository;
import com.omarkhaled.school_management.repository.SubjectRepository;
import com.omarkhaled.school_management.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
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
    public SubjectDTO assignStudentToSubject(Integer subjectId, Integer studentId) {
        var subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        subject.getStudents().add(student);
        subjectRepository.save(subject);

        return SubjectMapper.toDTO(subject);
    }

    public SubjectDTO assignTeacherToSubject(Integer subjectId, Integer teacherId) {
        var subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
        var teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        subject.setTeacher(teacher);
        subjectRepository.save(subject);

        return SubjectMapper.toDTO(subject);
    }
}
