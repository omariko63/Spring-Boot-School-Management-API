package com.omarkhaled.school_management.service;

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

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student updatedStudent) {
        Student student = getStudentById(id);

        if (updatedStudent.getName() != null) student.setName(updatedStudent.getName());
        if (updatedStudent.getEmail() != null) student.setEmail(updatedStudent.getEmail());
        if (updatedStudent.getGrade() != null) student.setGrade(updatedStudent.getGrade());

        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
