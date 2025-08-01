package com.omarkhaled.school_management.repository;

import com.omarkhaled.school_management.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
