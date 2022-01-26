package cz.spsmb.skolnisystemfx2.models;

import cz.spsmb.skolnisystemfx2.utils.SubjectGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private Map<String , List<Integer>> grades;
    private Map<String,Double> totalGrades;

    public Student(String name, Map<String, List<Integer>> grades ) {
        this.name = name;
        this.grades = grades;
        this.totalGrades = calculateTotalGrades();

    }


    private Map<String, Double> calculateTotalGrades() {
        Map<String, Double> map = new HashMap<>();
        for (String subject: SubjectGetter.getSubjects()) {
            map.put(subject, 0d);
        }

        for (String subject: map.keySet()) {
            if (map.get(subject) != null){
                double tempGrade = 0;
                for (int i:grades.get(subject)) {
                    tempGrade += i;
                }
                tempGrade /= grades.get(subject).size();
                map.replace(subject,tempGrade);
            }
        }

        return map;
    }

    public void recalculate(){
        this.totalGrades = calculateTotalGrades();
    }

    public String getName() {
        return name;
    }

    public Map<String, List<Integer>> getGrades() {
        return grades;
    }

    public Map<String, Double> getTotalGrades() {
        return totalGrades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(Map<String, List<Integer>> grades) {
        this.grades = grades;
    }
}
