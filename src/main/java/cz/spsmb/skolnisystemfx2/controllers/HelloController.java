package cz.spsmb.skolnisystemfx2.controllers;

import cz.spsmb.skolnisystemfx2.dialogs.AddStudentDialog;
import cz.spsmb.skolnisystemfx2.dialogs.EditDialog;
import cz.spsmb.skolnisystemfx2.dialogs.deleteDialog;
import cz.spsmb.skolnisystemfx2.models.Student;
import cz.spsmb.skolnisystemfx2.repository.Repository;
import cz.spsmb.skolnisystemfx2.repository.fileRepository;
import cz.spsmb.skolnisystemfx2.utils.SubjectGetter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    private Repository repository = new fileRepository();

    @FXML
    private ListView<String> listview = new ListView<>();

    public HelloController() throws IOException {
    }

    @FXML
    public void  newClicked(){
        AddStudentDialog dialog = new AddStudentDialog();
        Optional<List<String>> result = dialog.getDialog().showAndWait();

        if (result.isPresent()){
            if (repository.doesStudentExits(result.get().get(0))){
                List<String> list = result.get();

                String name = list.get(0);
                Map<String , List<Integer>> grades = new HashMap<>();

                for (String subject:SubjectGetter.getSubjects()) {
                    grades.put(subject,new ArrayList<>());
                }

                for (String s: list.get(2).trim().split(",")) {
                    grades.get(list.get(1)).add(Integer.parseInt(s));
                }

                repository.addStudent(new Student(name,grades));
                updateTable();

            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Student Already exists");
                alert.showAndWait();
            }
        }


    }

    @FXML
    public void editOnClick(){
        if (listview.getSelectionModel().getSelectedIndex() != -1){
            String nameE = listview.getItems().get(listview.getSelectionModel().getSelectedIndex()).split("  ")[0];
            EditDialog dialog = new EditDialog(repository.getStudent(nameE));
            Optional<List<String>> result = dialog.getDialog().showAndWait();
            dialog = null;
            System.out.println("nazdar2");
            if (result.isPresent()){
                System.out.println("nazdar3");
                if (!repository.doesStudentExits(result.get().get(0))){
                    System.out.println("nazdar4");
                    List<String> list = result.get();


                    String name = list.get(0);
                    Map<String , List<Integer>> grades = repository.getStudent(name).getGrades();


                    for (String s: list.get(2).trim().replace(" ","").split(",")) {
                        grades.get(list.get(1)).add(Integer.parseInt(s));
                    }
                    repository.removeStudent(list.get(0));
                    repository.addStudent(new Student(name,grades));
                    updateTable();

                }
            }else{
                System.out.println("result not present");
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please select a student");
            alert.showAndWait();
        }
    }

    @FXML
    public void deleteOnClick(){
        deleteDialog dialog = new deleteDialog();
        Optional<String> result = dialog.getDialog().showAndWait();

        if (result.isPresent()){
            if (repository.getStudent(result.get()) != null){
                repository.removeStudent(result.get());
            }
        }

        updateTable();

    }

    private void updateTable(){
        Set<Student> students = repository.getStudents();
        listview.getItems().clear();
        listview.getItems().add("name       |       TotalGrade");
        for (Student student:students) {
            String totalGrade = "";

            for (String subject: student.getTotalGrades().keySet()) {
                totalGrade += subject+": "+student.getTotalGrades().get(subject)+", ";
            }

            listview.getItems().add(student.getName()+"       |       "+totalGrade);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listview.getItems().add("name       |       TotalGrade");
        updateTable();
    }
}