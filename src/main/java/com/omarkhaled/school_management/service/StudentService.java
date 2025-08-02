package com.omarkhaled.school_management.service;

import com.omarkhaled.school_management.dto.StudentDTO;
import com.omarkhaled.school_management.mapper.StudentMapper;
import com.omarkhaled.school_management.model.Student;
import com.omarkhaled.school_management.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
    }

    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return StudentMapper.toDTO(student);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    public StudentDTO updateStudent(Integer id, StudentDTO updatedStudentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (updatedStudentDTO.name() != null) student.setName(updatedStudentDTO.name());
        if (updatedStudentDTO.email() != null) student.setEmail(updatedStudentDTO.email());
        if (updatedStudentDTO.grade() != null) student.setGrade(updatedStudentDTO.grade());

        return StudentMapper.toDTO(studentRepository.save(student));
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
