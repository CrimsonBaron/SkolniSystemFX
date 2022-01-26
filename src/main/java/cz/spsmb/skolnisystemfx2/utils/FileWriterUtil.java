package cz.spsmb.skolnisystemfx2.utils;

import cz.spsmb.skolnisystemfx2.models.Student;

import java.io.*;
import java.util.*;

public class FileWriterUtil {

    private File file;
    private FileWriter fileWriter;
    public Scanner sc ;

    public FileWriterUtil() throws IOException {
        file = new File("Students.csv");
        fileWriter = new FileWriter(file,true);
        sc = new Scanner(new FileInputStream(file));
    }

    public void write(Set<Student> students) throws IOException {

        new FileOutputStream(file).close();
        fileWriter = new FileWriter(file,true);
        String text = "name;grades;totalGrades\n";

       if (!students.isEmpty()){
           for (Student s: students) {
               String gradesFormat = "";

               for (String subject: s.getGrades().keySet()) {
                   gradesFormat += subject;
                   for (int i:s.getGrades().get(subject)) {
                       gradesFormat +="-"+i;
                   }
                   gradesFormat +=",";
               }
              /* String totalGradesFormat ="";
               for (String subject:s.getTotalGrades().keySet()) {
                   totalGradesFormat +=subject+"-"+s.getTotalGrades().get(subject)+",";
               }*/

               text += s.getName()+";"+gradesFormat+";"+"\n";
           }

           fileWriter.write(text);
           fileWriter.flush();
           fileWriter.close();

       }
    }

    public Set<Student> load(){
        if (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
        Set<Student> students = new HashSet<>();

        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] elements = line.split(";");

            String name = elements[0];
            String gradesFormat = elements[1];
            //String totalGradeFormat = elements[2];

            Map<String, List<Integer>> grades = new HashMap<>();
            for (String s :gradesFormat.split(",")) {
                grades.put(s.split("-")[0], new ArrayList<>());
                for (int i = 1; i <s.split("-").length ; i++) {
                    grades.get(s.split("-")[0]).add(Integer.parseInt(s.split("-")[i]));
                }
            }

        /*Map<String, Double> totalgrade = new HashMap<>();
        for (String s:totalGradeFormat.split(",")) {
            totalgrade.put(s.split("-")[0],Double.parseDouble(s.split("-")[1]));
        }*/

            students.add(new Student(name,grades));
        }


        return students;

    }

}
