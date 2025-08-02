package com.omarkhaled.school_management.mapper;

import com.omarkhaled.school_management.dto.*;
import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherDTO toDTO(Teacher teacher) {
        List<SubjectSummaryDTO> subjectDTOs = teacher.getSubjects() == null ?
                List.of() :
                teacher.getSubjects().stream()
                        .map(subject -> new SubjectSummaryDTO(subject.getId(), subject.getName()))
                        .collect(Collectors.toList());

        return new TeacherDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                subjectDTOs
        );
    }

    public static TeacherSummaryDTO toSummaryDTO(Teacher teacher) {
        return new TeacherSummaryDTO(teacher.getId(), teacher.getName());
    }

    public static Teacher toEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.id());
        teacher.setName(dto.name());
        teacher.setEmail(dto.email());
        return teacher;
    }
}
