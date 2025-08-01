package com.omarkhaled.school_management.repository;

import com.omarkhaled.school_management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
