package cz.spsmb.skolnisystemfx2.dialogs;

import cz.spsmb.skolnisystemfx2.utils.SubjectGetter;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class deleteDialog {
    private Dialog<String> dialog;
    private ButtonType okButtonType;
    private GridPane grid;
    private TextField name;

    public deleteDialog() {
        dialog = new Dialog<>();
        dialog.setTitle("Delete Student");
        dialog.initStyle(StageStyle.UTILITY);

        okButtonType = new ButtonType("DELETE", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        name = new TextField();
        name.setPromptText("name");

        grid.add(new Label("name"),0,0);
        grid.add(name,1,0);


        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return name.getText();
            }
            return null;
        });

    }

    public Dialog<String> getDialog() {
        return dialog;
    }


}
