package com.example.studentcrud;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class StudentFormController {
    private final Scene scene;
    private final MainApp app;
    private final StudentDAO dao;
    private final Student studentToEdit;

    private final TextField rollField = new TextField();
    private final TextField nameField = new TextField();

    public StudentFormController(MainApp app,
                                 StudentDAO dao,
                                 Student studentToEdit) {
        this.app = app;
        this.dao = dao;
        this.studentToEdit = studentToEdit;

        boolean editMode = studentToEdit != null;

        Label title = new Label(editMode ? "Update Student" : "Add Student");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        rollField.setPromptText("Example: 101");
        nameField.setPromptText("Example: Ayesha Rahman");

        if (editMode) {
            rollField.setText(String.valueOf(studentToEdit.getRoll()));
            nameField.setText(studentToEdit.getName());
        }

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.add(new Label("Student Roll:"), 0, 0);
        form.add(rollField, 1, 0);
        form.add(new Label("Student Name:"), 0, 1);
        form.add(nameField, 1, 1);

        Button saveButton = new Button(editMode
                ? "Update Student"
                : "Save Student");
        Button clearButton = new Button("Clear");
        Button listButton = new Button("View Students");

        saveButton.setOnAction(e -> saveOrUpdate());
        clearButton.setOnAction(e -> {
            rollField.clear();
            nameField.clear();
            rollField.requestFocus();
        });
        listButton.setOnAction(e -> app.showListScene());

        HBox buttons = new HBox(10, saveButton, clearButton, listButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(18, title, form, buttons);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.TOP_CENTER);

        this.scene = new Scene(root, 560, 330);
    }

    private void saveOrUpdate() {
        String rollText = rollField.getText().trim();
        String name = nameField.getText().trim();

        if (rollText.isEmpty() || name.isEmpty()) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Roll and name are required.");
            return;
        }

        final int roll;
        try {
            roll = Integer.parseInt(rollText);
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Roll must be an integer.");
            return;
        }

        if (roll <= 0) {
            showAlert(Alert.AlertType.WARNING,
                    "Validation",
                    "Roll must be greater than 0.");
            return;
        }

        Student newData = new Student(roll, name);

        try {
            if (studentToEdit == null) {
                // CREATE
                if (dao.rollExists(roll)) {
                    showAlert(Alert.AlertType.WARNING,
                            "Duplicate Roll",
                            "A student with this roll already exists.");
                    return;
                }
                dao.insertStudent(newData);
                showAlert(Alert.AlertType.INFORMATION,
                        "Success",
                        "Student saved successfully.");
            } else {
                // UPDATE
                int oldRoll = studentToEdit.getRoll();

                if (roll != oldRoll && dao.rollExists(roll)) {
                    showAlert(Alert.AlertType.WARNING,
                            "Duplicate Roll",
                            "The new roll already belongs to another student.");
                    return;
                }

                dao.updateStudent(oldRoll, newData);
                showAlert(Alert.AlertType.INFORMATION,
                        "Success",
                        "Student updated successfully.");
            }

            app.showListScene();

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR,
                    "Database Error",
                    ex.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}
