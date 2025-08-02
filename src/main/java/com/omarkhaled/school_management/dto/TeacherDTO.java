package com.omarkhaled.school_management.dto;

import java.util.List;

public record TeacherDTO(Integer id, String name, String email, List<SubjectSummaryDTO> subjects) {
}
