package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class VirementController implements Initializable {

    @FXML
    private Button btnval;

    @FXML
    private TableColumn<Virement, String> colda;

    @FXML
    private TableColumn<Virement, Integer> colidce;

    @FXML
    private TableColumn<Virement, Integer> colidcr;

    @FXML
    private TableColumn<Virement, Integer> colidvr;

    @FXML
    private TableColumn<Virement, Double> colso;

    @FXML
    private Label idl;

    @FXML
    private Button idlogout;

    @FXML
    private Label lblvr;

    @FXML
    private TextField tda;

    @FXML
    private TextField tidce;

    @FXML
    private TextField tidcr;

    @FXML
    private TextField tidv;

    @FXML
    private TextField tso;

    @FXML
    private TableView<Virement> tvVr;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showVirements();
    }
    public void showVirements() {
        ObservableList<Virement> list = getVirement();
        colidvr.setCellValueFactory(new PropertyValueFactory<Virement, Integer>("Id"));
        colda.setCellValueFactory(new PropertyValueFactory<Virement, String>("Date"));
        colidce.setCellValueFactory(new PropertyValueFactory<Virement, Integer>("IdCompteExp"));
        colidcr.setCellValueFactory(new PropertyValueFactory<Virement, Integer>("IdCompteRec"));
        colso.setCellValueFactory(new PropertyValueFactory<Virement, Double>("Somme"));
        tvVr.setItems(list);
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

    public ObservableList<Virement> getVirement() {
        ObservableList<Virement> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Virement";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Virement virement;
            while (rs.next()) {
                virement = new Virement(rs.getInt("IdVir"), rs.getString("Date"), rs.getInt("IdCompteExp"), rs.getInt("IdCompteRec"), rs.getDouble("Somme"));
                list.add(virement);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return list;
    }
    public ArrayList<Compte> getCompteVr() {
        ObservableList<Compte> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Compte ";
        Statement st;
        ResultSet rs;

        Compte compte = new Compte(0, -1.0);
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                compte = new Compte(rs.getInt("CodeCompte"), rs.getString("DateCreation"), rs.getDouble("Solde"), rs.getInt("client"), rs.getString("Type"));
                list.add(compte);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        ArrayList<Compte> listvr = new ArrayList<Compte>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCodeCompte() == parseInt(tidce.getText())) {
                listvr.add(list.get(i));

            }

        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCodeCompte() == parseInt(tidcr.getText())) {
                listvr.add(list.get(i));
            }
        }


        return listvr;

    }

    @FXML
    public void HandleOnActionval(ActionEvent e) throws IOException {
        if ((tidv.getText().isEmpty() && tda.getText().isEmpty() && tidce.getText().isEmpty() && tidcr.getText().isEmpty() && tso.getText().isEmpty()) || (tidv.getText().isEmpty() || tda.getText().isEmpty() || tidce.getText().isEmpty() || tidcr.getText().isEmpty() || tso.getText().isEmpty())) {
            lblvr.setText("Veuillez remplir tous les champs s'il vous plait !! ");
        }
        ArrayList<Compte> lvr = getCompteVr();
        if (e.getSource() == btnval && lvr.get(0).getSolde() > Double.parseDouble(tso.getText())) {
            insertv();

            lvr.get(1).setSolde(lvr.get(1).getSolde() + Double.parseDouble(tso.getText()));
            System.out.println(lvr.get(1).getSolde());

            String qr = "UPDATE Compte SET  Solde='" + lvr.get(1).getSolde() + "' WHERE  CodeCompte= '" + lvr.get(1).getCodeCompte() + "'";
            executeQuery(qr);

            lvr.get(0).setSolde(lvr.get(0).getSolde() - Double.parseDouble(tso.getText()));
            System.out.println(lvr.get(0).getSolde());

            String qr1 = "UPDATE Compte SET  Solde='" + lvr.get(0).getSolde() + "' WHERE  CodeCompte= '" + lvr.get(0).getCodeCompte() + "'";
            executeQuery(qr1);
            lblvr.setText("Virement effectué ! ");
        } else
            lblvr.setText("Virement impossible !! ");


    }
    public void insertv() {

        String query = "INSERT INTO Virement  VALUES('" + tidv.getText() + "','" + tda.getText() + "','" + tidce.getText() +
                "','" + tidcr.getText() + "','" + tso.getText() + "')";
        executeQuery(query);
        System.out.println(query);
        showVirements();

    }

    @FXML
    void clictv(MouseEvent event) {
        Virement virement = (Virement) tvVr.getSelectionModel().getSelectedItem();
        System.out.println(virement.getId());
        System.out.println(virement.getDate());
        System.out.println(virement.getIdCompteExp());
        System.out.println(virement.getIdCompteRec());
        System.out.println(virement.getSomme());
        tidv.setText("" + virement.getId());
        tda.setText(virement.getDate());
        tidce.setText("" + virement.getIdCompteExp());
        tidcr.setText("" + virement.getIdCompteRec());
        tso.setText("" + virement.getSomme());
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage =(Stage) idlogout.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setScene(new Scene(root ));
        primaryStage.show();
        /*HelloApplication m = new HelloApplication();
        m.ChangeScene("Login.fxml");*/
    }

}
