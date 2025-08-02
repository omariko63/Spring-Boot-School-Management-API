package com.omarkhaled.school_management.service;

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

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Integer id, Teacher updatedTeacher) {
        Teacher teacher = getTeacherById(id);

        if (updatedTeacher.getName() != null) teacher.setName(updatedTeacher.getName());
        if (updatedTeacher.getEmail() != null) teacher.setEmail(updatedTeacher.getEmail());

        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }
}
