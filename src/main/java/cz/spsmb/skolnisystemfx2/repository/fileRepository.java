package cz.spsmb.skolnisystemfx2.repository;

import cz.spsmb.skolnisystemfx2.models.Student;
import cz.spsmb.skolnisystemfx2.utils.FileWriterUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class fileRepository implements Repository {

    private Set<Student> students;
    private FileWriterUtil fileWriterUtil;

    public fileRepository() throws IOException {
        fileWriterUtil = new FileWriterUtil();
        students = fileWriterUtil.load();
    }

    @Override
    public void removeStudent(String name) {
        Student studentToRemove = null;

        for (Student student: students) {
            if (student.getName().equals(name)){
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null){
            students.remove(studentToRemove);
            System.out.println("nazdar");
        }else{
            System.out.println("null");
        }

        try {
            fileWriterUtil.write(students);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
        System.out.println(student.toString());
        try {
            fileWriterUtil.write(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public Student getStudent(String name) {

        Student wantedStudent = null;

        for (Student student: students) {
            if (student.getName().equals(name)){
                wantedStudent = student;
                break;
            }
        }

        return wantedStudent;
    }

    @Override
    public boolean doesStudentExits(String name) {
        return getStudent(name) == null;
    }
}
