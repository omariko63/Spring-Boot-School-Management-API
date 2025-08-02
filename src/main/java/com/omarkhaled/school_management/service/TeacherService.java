package com.omarkhaled.school_management.service;

import com.omarkhaled.school_management.dto.TeacherDTO;
import com.omarkhaled.school_management.mapper.TeacherMapper;
import com.omarkhaled.school_management.model.Teacher;
import com.omarkhaled.school_management.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::toDTO)
                .toList();
    }

    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
        return TeacherMapper.toDTO(teacher);
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = TeacherMapper.toEntity(teacherDTO);
        return TeacherMapper.toDTO(teacherRepository.save(teacher));
    }

    public TeacherDTO updateTeacher(Integer id, TeacherDTO updatedTeacher) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        if (updatedTeacher.name() != null) teacher.setName(updatedTeacher.name());
        if (updatedTeacher.email() != null) teacher.setEmail(updatedTeacher.email());

        return TeacherMapper.toDTO(teacherRepository.save(teacher));
    }

    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }
}
