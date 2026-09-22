package org.example.database_practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Student Management");
        showFormScene();
        primaryStage.show();
    }

    public static void showFormScene() {
        loadScene("StudentForm.fxml");
    }

    public static void showListScene() {
        loadScene("StudentList.fxml");
    }

    private static void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(StudentApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Could not load " + fxmlFile + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}