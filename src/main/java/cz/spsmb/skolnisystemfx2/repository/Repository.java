package cz.spsmb.skolnisystemfx2.repository;

import cz.spsmb.skolnisystemfx2.models.Student;

import java.util.List;
import java.util.Set;

public interface Repository {
    void removeStudent(String name);
    void addStudent(Student student);
    Set<Student> getStudents();
    Student getStudent(String name);
    boolean doesStudentExits(String name);




}
