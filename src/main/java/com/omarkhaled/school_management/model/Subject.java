package com.omarkhaled.school_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = ("subject_students"),
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @Column(nullable = false)
    private Integer capacity;

    public Subject(){}

    public Subject(String name, String description, Teacher teacher, List<Student> students, Integer capacity) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = students;
        this.capacity = capacity;
    }

    public Subject(String name, String description, Teacher teacher, Integer capacity) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = new ArrayList<>();
        this.capacity = capacity;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean addStudent(Student student){
        if(students.size() >= capacity){
            throw new IllegalStateException("Capacity Exceeded");
        }
        else if(!students.contains(student)){
            students.add(student);
            student.addSubjectInternal(this);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean removeStudent(Student student){
        if( !students.remove(student)){
            throw new IllegalStateException("Student Not Found");
        }
        else{
            student.removeSubjectInternal(this);
            return true;
        }
    }
}
