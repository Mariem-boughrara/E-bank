package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btncompte;
    @FXML
    private Button btnemploye;
    @FXML
    private Button btnclient;
    @FXML
    private ImageView imgid;

    // Event Listener on Button[#btncompte].onAction
    @FXML
    public void HandleOnAction1(ActionEvent event) throws IOException {
        Stage stage = (Stage) btncompte.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Compte.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void HandleOnAction3(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnclient.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
        primaryStage.setScene(new Scene(root, 843, 483));
        primaryStage.show();
    }

    @FXML
    public void HandleOnAction2(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnemploye.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primaryStage.setScene(new Scene(root, 843, 483));
        primaryStage.show();
    }
}