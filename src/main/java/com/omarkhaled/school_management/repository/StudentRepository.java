package com.omarkhaled.school_management.repository;

import com.omarkhaled.school_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
