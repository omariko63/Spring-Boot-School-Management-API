package com.omarkhaled.school_management.dto;

import java.util.List;

public record SubjectDTO(Integer id, String name, String description, List<StudentSummaryDTO> students, TeacherSummaryDTO teacherDTO) {
}
