package org.example.database_practice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class StudentListController {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Integer> rollColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    private final StudentDAO dao = new StudentDAO();

    @FXML
    public void initialize() {
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        loadData();
    }

    private void loadData() {
        try {
            List<Student> students = dao.getAllStudents();
            ObservableList<Student> data = FXCollections.observableArrayList(students);
            studentTable.setItems(data);
        } catch (SQLException e) {
            showAlert("Could not load student list: " + e.getMessage());
        }
    }

    @FXML
    protected void handleBack() {
        StudentApplication.showFormScene();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}