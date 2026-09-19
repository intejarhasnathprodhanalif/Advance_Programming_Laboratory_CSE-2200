package com.example.studentcrud;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;
    private StudentDAO studentDAO;

    @Override
    public void start(Stage stage) {
        Database.initializeDatabase();

        this.primaryStage = stage;
        this.studentDAO = new StudentDAO();

        primaryStage.setTitle("Student Management - JavaFX + SQLite");
        showFormScene(null);
        primaryStage.show();
    }

    public void showFormScene(Student studentToEdit) {
        StudentFormController formScene =
                new StudentFormController(this, studentDAO, studentToEdit);
        primaryStage.setScene(formScene.getScene());
    }

    public void showListScene() {
        StudentListScene listScene =
                new StudentListScene(this, studentDAO);
        primaryStage.setScene(listScene.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

