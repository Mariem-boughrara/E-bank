package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Welcome page !");

        stage.show();
    }

    public void ChangeScene(String xml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource((xml)));
        stg.getScene().setRoot(root);
    }

    public static void main(String[] args) {
        launch();

    }
}
