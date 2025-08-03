package com.omarkhaled.school_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message ="")
    private String name;

    @Column(nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    @Min(1) @Max(12)
    private Integer grade;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    public Student(String name, String email, Integer grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        subjects = new ArrayList<>();
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
    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject){
        if(!subjects.contains(subject)){
            subjects.add(subject);
            subject.addStudent(this);
        }
    }
    protected void addSubjectInternal(Subject subject){
        if(!subjects.contains(subject)){
            subjects.add(subject);
        }
    }
    public void removeSubject(Subject subject){
        if(!subjects.contains(subject)){
            throw new NullPointerException("Subject not found");
        }
        else{
            subjects.remove(subject);
        }
    }
    protected void removeSubjectInternal(Subject subject){
        subjects.remove(subject);
    }

}
