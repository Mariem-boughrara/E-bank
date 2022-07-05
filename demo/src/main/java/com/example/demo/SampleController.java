package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SampleController implements Initializable {
    @FXML
    private Button idretour;
    @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<Employe, String> coladr;

    @FXML
    private TableColumn<Employe, Integer> colcode;

    @FXML
    private TableColumn<Employe, String> colnom;

    @FXML
    private TableColumn<Employe, String> colprenom;

    @FXML
    private TextField tadr;

    @FXML
    private TextField tcode;

    @FXML
    private TextField tnom;

    @FXML
    private TextField tprenom;
    @FXML
    private Label lbl;
    @FXML
    private Label idl;
    @FXML
    private TableView<Employe> tvemploye;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployes();

    }

    @FXML
    void HandleButtonAction(ActionEvent e) {
        if ((tcode.getText().isEmpty() && tnom.getText().isEmpty() && tprenom.getText().isEmpty() && tadr.getText().isEmpty()) || (tcode.getText().isEmpty() || tnom.getText().isEmpty() || tprenom.getText().isEmpty() || tadr.getText().isEmpty())) {
            lbl.setText("Veuillez remplir tous les champs s'il vous plait !! ");
        }
        if (e.getSource() == btninsert) {
            insert();
        } else if (e.getSource() == btnupdate) {
            update();
        } else if (e.getSource() == btndelete) {
            delete();
        }

    }

    public Connection getconnection() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_comptes_bancaires", "root", "");
            return con;

        } catch (Exception ex) {
            System.out.println("error:" + ex.getMessage());

        }
        return null;
    }

    public ObservableList<Employe> getEmploye() {
        ObservableList<Employe> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Employe";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Employe employe;
            while (rs.next()) {
                employe = new Employe(rs.getInt("CodeEmploye"), rs.getString("Nomemploye"), rs.getString("PrenomEmploye"), rs.getString("Adresse"));
                list.add(employe);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    public void showEmployes() {
        ObservableList<Employe> list = getEmploye();
        colcode.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("CodeEmploye"));
        colnom.setCellValueFactory(new PropertyValueFactory<Employe, String>("NomEmploye"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Employe, String>("PrenomEmploye"));
        coladr.setCellValueFactory(new PropertyValueFactory<Employe, String>("Adresse"));
        tvemploye.setItems(list);

    }

    public void insert() {
        String query = "INSERT INTO Employe  VALUES('" + tcode.getText() + "','" + tnom.getText() + "','" + tprenom.getText() +
                "','" + tadr.getText() + "')";
        executeQuery(query);
        showEmployes();
    }

    public void update() {
        String qr = "UPDATE Employe SET  CodeEmploye='" + tcode.getText() + "', NomEmploye='" + tnom.getText() + "',PrenomEmploye='" + tprenom.getText() + "',Adresse='" + tadr.getText() +"' WHERE  CodeEmploye= '" + tcode.getText() + "'";

        executeQuery(qr);
        showEmployes();
    }

    public void delete() {
        String qr = "DELETE FROM Employe WHERE CodeEmploye= '" + tcode.getText() + "'";
        executeQuery(qr);
        showEmployes();
    }

    private void executeQuery(String query) {
        Connection conn = getconnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clictv(javafx.scene.input.MouseEvent mouseEvent) {
        Employe employe = (Employe) tvemploye.getSelectionModel().getSelectedItem();
        System.out.println(employe.getCodeEmploye());
        System.out.println(employe.getNomEmploye());
        System.out.println(employe.getPrenomEmploye());
        System.out.println(employe.getAdresse());
        tcode.setText("" + employe.getCodeEmploye());
        tnom.setText(employe.getNomEmploye());
        tprenom.setText(employe.getPrenomEmploye());
        tadr.setText(employe.getAdresse());

    }

    @FXML
    void retour(ActionEvent e) throws IOException {
        Stage stage =(Stage) idretour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        primaryStage.setScene(new Scene(root ));
        primaryStage.show();
        /*HelloApplication m = new HelloApplication();
        m.ChangeScene("accueil.fxml");*/
    }

}
