
//Studentformcontroller · JAVA
        package org.example.database_practice;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.database_practice.StudentApplication;
import org.example.database_practice.StudentDAO;

import java.sql.SQLException;

public class StudentFormController {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField rollInput;

    private final StudentDAO dao = new StudentDAO();

    @FXML
    protected void handleSave() {
        String name = nameInput.getText().trim();
        String rollText = rollInput.getText().trim();

        if (name.isEmpty() || rollText.isEmpty()) {
            showAlert("Roll and name are required.");
            return;
        }

        int roll;
        try {
            roll = Integer.parseInt(rollText);
        } catch (NumberFormatException e) {
            showAlert("Roll must be a number.");
            return;
        }

        try {
            dao.addDataList(roll, name);
            nameInput.clear();
            rollInput.clear();
        } catch (SQLException e) {
            showAlert("org.example.database_practice.Database error: " + e.getMessage());
        }
    }

    @FXML
    protected void handleViewList() {
        StudentApplication.showListScene();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

