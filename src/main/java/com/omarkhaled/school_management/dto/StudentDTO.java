package com.omarkhaled.school_management.dto;

import java.util.List;

public record StudentDTO(Integer id, String name, String email, Integer grade, List<SubjectSummaryDTO> subjects) {
}

