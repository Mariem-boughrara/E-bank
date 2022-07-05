package com.example.demo;

import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button btnlogin;
    @FXML
    private Label idmsg;
    @FXML
    private ImageView idpass;

    @FXML
    private ImageView iduser;


    @FXML
    private PasswordField tpass;

    @FXML
    private TextField tusername;
    @FXML
    private ImageView idimg;
    @FXML
    private ImageView idacc;

    @FXML
    void login(ActionEvent event) throws IOException {
        if (tusername.getText().isEmpty() && tpass.getText().isEmpty()) {
            idmsg.setText("Veuillez remplir les champs s'il vous plait !");
        } else if (tusername.getText().equals("admin") && tpass.getText().equals("adminadmin")) {
           /* Stage stage =(Stage) btnlogin.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("views/accueil.fxml"));
            primaryStage.setScene(new Scene(root , 843,483));
            primaryStage.show();*/
            HelloApplication m = new HelloApplication();
            m.ChangeScene("accueil.fxml");
        } else {
            idmsg.setText("nom d'utilisateur ou mot de passe incorrect !!");
        }

    }


}