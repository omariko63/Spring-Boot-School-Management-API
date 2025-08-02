package com.omarkhaled.school_management.mapper;

import com.omarkhaled.school_management.model.Student;
import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.dto.StudentDTO;
import com.omarkhaled.school_management.dto.SubjectSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDTO toDTO(Student student) {
        List<SubjectSummaryDTO> subjectDTOs = student.getSubjects() == null ?
                List.of() :
                student.getSubjects().stream()
                        .map(subject -> new SubjectSummaryDTO(subject.getId(), subject.getName()))
                        .collect(Collectors.toList());

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getGrade(),
                subjectDTOs
        );
    }

    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.id());
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setGrade(dto.grade());
        return student;
    }
}
