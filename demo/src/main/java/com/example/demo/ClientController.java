package com.example.demo;

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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private Button idretour;
    @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<Client, String> colacl;

    @FXML
    private TableColumn<Client, Integer> colcl;

    @FXML
    private TableColumn<Client, String> colncl;

    @FXML
    private TableColumn<Client, String> colpcl;

    @FXML
    private TextField tacl;
    @FXML
    private Label lbl;
    @FXML
    private TextField tcl;
    @FXML
    private Label idl;
    @FXML
    private TextField tncl;

    @FXML
    private TextField tpcl;

    @FXML
    private TableView<Client> tvClient;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showClients();

    }

    @FXML
    void HandleButtonAction(ActionEvent e) {
        if ((tcl.getText().isEmpty() && tncl.getText().isEmpty() && tpcl.getText().isEmpty() && tacl.getText().isEmpty()) || (tcl.getText().isEmpty() || tncl.getText().isEmpty() || tpcl.getText().isEmpty() || tacl.getText().isEmpty())) {
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

    public ObservableList<Client> getClient() {
        ObservableList<Client> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Client";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Client client;
            while (rs.next()) {
                client = new Client(rs.getInt("CodeClient"), rs.getString("NomClient"), rs.getString("PrenomClient"), rs.getString("AdresseClient"));
                list.add(client);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    public void showClients() {
        ObservableList<Client> list = getClient();
        colcl.setCellValueFactory(new PropertyValueFactory<Client, Integer>("CodeClient"));
        colncl.setCellValueFactory(new PropertyValueFactory<Client, String>("NomClient"));
        colpcl.setCellValueFactory(new PropertyValueFactory<Client, String>("PrenomClient"));
        colacl.setCellValueFactory(new PropertyValueFactory<Client, String>("AdresseClient"));
        tvClient.setItems(list);

    }

    public void insert() {
        String query = "INSERT INTO Client  VALUES('" + tcl.getText() + "','" + tncl.getText() + "','" + tpcl.getText() +
                "','" + tacl.getText() + "')";
        executeQuery(query);
        System.out.println(query);
        showClients();
    }

    public void update() {
        String qr = "UPDATE Client SET  CodeClient='" + tcl.getText() + "', NomClient='" + tncl.getText() + "',PrenomClient='" + tpcl.getText() + "',AdresseClient='" + tacl.getText() +  "' WHERE  CodeClient= '" + tcl.getText() + "'";
        executeQuery(qr);
        showClients();
    }

    public void delete() {
        String qr = "DELETE FROM Client WHERE CodeClient= '" + tcl.getText() + "'";
        executeQuery(qr);
        showClients();
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
        Client client = (Client) tvClient.getSelectionModel().getSelectedItem();
        System.out.println(client.getCodeClient());
        System.out.println(client.getNomClient());
        System.out.println(client.getPrenomClient());
        System.out.println(client.getAdresseClient());
        tcl.setText("" + client.getCodeClient());
        tncl.setText(client.getNomClient());
        tpcl.setText(client.getPrenomClient());
        tacl.setText(client.getAdresseClient());

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
