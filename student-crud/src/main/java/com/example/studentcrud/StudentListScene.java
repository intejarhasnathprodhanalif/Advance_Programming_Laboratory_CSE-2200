package com.example.studentcrud;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.SQLException;
import java.util.Optional;

public class StudentListScene {
    private final Scene scene;
    private final MainApp app;
    private final StudentDAO dao;
    private final TableView<Student> table = new TableView<>();

    public StudentListScene(MainApp app, StudentDAO dao) {
        this.app = app;
        this.dao = dao;

        Label title = new Label("Student List");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TableColumn<Student, Integer> rollColumn =
                new TableColumn<>("Roll");
        rollColumn.setCellValueFactory(cell ->
                new SimpleIntegerProperty(
                        cell.getValue().getRoll()).asObject());
        rollColumn.setPrefWidth(140);

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().getName()));
        nameColumn.setPrefWidth(320);

        table.getColumns().addAll(rollColumn, nameColumn);
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        Button addButton = new Button("Add New");
        Button editButton = new Button("Edit Selected");
        Button deleteButton = new Button("Delete Selected");
        Button refreshButton = new Button("Refresh");

        addButton.setOnAction(e -> app.showFormScene(null));
        editButton.setOnAction(e -> editSelected());
        deleteButton.setOnAction(e -> deleteSelected());
        refreshButton.setOnAction(e -> loadStudents());
        HBox buttons = new HBox(
                10, addButton, editButton, deleteButton, refreshButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, title, table, buttons);
        root.setPadding(new Insets(20));

        this.scene = new Scene(root, 650, 450);
        loadStudents();
    }

    // READ
    private void loadStudents() {
        try {
            table.setItems(FXCollections.observableArrayList(
                    dao.getAllStudents()));
        } catch (SQLException ex) {
            showError("Could not load students: " + ex.getMessage());
        }
    }

    private void editSelected() {
        Student selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning("Select a student first.");
            return;
        }

        app.showFormScene(selected);
    }

    // DELETE
    private void deleteSelected() {
        Student selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning("Select a student first.");
            return;
        }

        Alert confirm = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete roll " + selected.getRoll() + "?",
                ButtonType.YES,
                ButtonType.NO
        );
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                dao.deleteStudent(selected.getRoll());
                loadStudents();
            } catch (SQLException ex) {
                showError("Could not delete student: " + ex.getMessage());
            }
        }
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}
