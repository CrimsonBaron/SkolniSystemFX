package cz.spsmb.skolnisystemfx2.dialogs;

import cz.spsmb.skolnisystemfx2.models.Student;
import cz.spsmb.skolnisystemfx2.utils.SubjectGetter;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class EditDialog {
    private Dialog<List<String>> dialog;
    private ButtonType okButtonType;
    private GridPane grid;
    private ComboBox<String> subject;
    private TextField name;
    private TextField grades;

    public EditDialog(Student student) {
        dialog = new Dialog<>();
        dialog.setTitle("New Student");
        dialog.initStyle(StageStyle.UTILITY);

        okButtonType = new ButtonType("EDIT", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        name = new TextField();
        name.setPromptText("name");
        name.setText(student.getName());
        subject = new ComboBox<>();
        subject.getItems().addAll(SubjectGetter.getSubjects());
        grades = new TextField();
        grades.setPromptText("1,2,1,1");

        subject.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String text = String.valueOf(student.getGrades().get(newValue)).replace("[","").replace("]","");
            System.out.println(text);
            grades.setText(text);
        });

        grid.add(new Label("name"),0,0);
        grid.add(name,1,0);
        grid.add(new Label("subject"),0,1);
        grid.add(subject,1,1);
        grid.add(new Label("grades"),0,2);
        grid.add(grades,1,2);

        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                List<String> list = new ArrayList<>();

                list.add(name.getText());
                list.add(subject.getValue());
                list.add(grades.getText());
                System.out.println(grades.getText());
                return list;
            }
            return null;
        });

    }

    public Dialog<List<String>> getDialog() {
        return dialog;
    }
}
