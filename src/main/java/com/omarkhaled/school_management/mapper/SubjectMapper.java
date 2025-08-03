package com.omarkhaled.school_management.mapper;

import com.omarkhaled.school_management.dto.*;
import com.omarkhaled.school_management.model.Subject;
import com.omarkhaled.school_management.model.Student;
import com.omarkhaled.school_management.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        List<StudentSummaryDTO> studentDTOs = subject.getStudents() == null ?
                List.of() :
                subject.getStudents().stream()
                        .map(student -> new StudentSummaryDTO(student.getId(), student.getName()))
                        .collect(Collectors.toList());

        TeacherSummaryDTO teacherDTO = subject.getTeacher() == null ?
                null :
                new TeacherSummaryDTO(subject.getTeacher().getId(), subject.getTeacher().getName());

        return new SubjectDTO(
                subject.getId(),
                subject.getName(),
                subject.getDescription(),
                studentDTOs,
                teacherDTO,
                subject.getCapacity()
        );
    }

    public static SubjectSummaryDTO toSummaryDTO(Subject subject) {
        return new SubjectSummaryDTO(subject.getId(), subject.getName());
    }

    public static Subject toEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setId(dto.id());
        subject.setName(dto.name());
        subject.setDescription(dto.description());
        subject.setCapacity(dto.capacity());
        return subject;
    }
}
