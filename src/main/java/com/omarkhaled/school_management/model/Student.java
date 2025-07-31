package com.omarkhaled.school_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name ="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    @Min(1) @Max(12)
    private Integer grade;

    public Student(String name, String email, Integer grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    public Student(){}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
