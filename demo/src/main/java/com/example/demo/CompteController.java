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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CompteController implements Initializable {
    @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;
    @FXML
    private Button btnvirement;
    @FXML
    private Button btntransaction;

    @FXML
    private TableColumn<Compte, Integer> colc;

    @FXML
    private TableColumn<Compte, Integer> colcc;

    @FXML
    private TableColumn<Compte, String> coldc;

    @FXML
    private TableColumn<Compte, String> colt;

    @FXML
    private TableColumn<Compte, Double> cols;

    @FXML
    private TextField tc;

    @FXML
    private TextField tcc;

    @FXML
    private TextField tdc;

    @FXML
    private Button idretour;

    @FXML
    private TextField ts;

    @FXML
    private TableView<Compte> tvCompte;
    @FXML
    private ComboBox<String> tt;
    @FXML
    private Label lbl;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showComptes();
        ObservableList<String> list = FXCollections.observableArrayList("Compte courant", "Compte Epargne");
        tt.setItems(list);


    }

    @FXML
    void HandleButtonAction(ActionEvent e) throws IOException {
        if ((tc.getText().isEmpty() && tcc.getText().isEmpty() && tdc.getText().isEmpty() && ts.getText().isEmpty()) || (tc.getText().isEmpty() || tcc.getText().isEmpty() || tdc.getText().isEmpty() || ts.getText().isEmpty())) {
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

    @FXML
    public void HandleButtonActiontr(ActionEvent event) throws IOException {
            Stage stage =(Stage) btntransaction.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
            primaryStage.setScene(new Scene(root ));
            primaryStage.show();
        /*HelloApplication m = new HelloApplication();
        m.ChangeScene("Transaction.fxml");*/
    }

    @FXML
    public void HandleButtonActionvr(ActionEvent event) throws IOException {
            Stage stage =(Stage) btnvirement.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Virement.fxml"));
            primaryStage.setScene(new Scene(root ));
            primaryStage.show();
        /*HelloApplication m = new HelloApplication();
        m.ChangeScene("Virement.fxml");*/
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

    public ObservableList<Compte> getCompte() {
        ObservableList<Compte> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Compte";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Compte compte;
            while (rs.next()) {
                compte = new Compte(rs.getInt("CodeCompte"), rs.getString("DateCreation"), rs.getDouble("Solde"), rs.getInt("client"), rs.getString("Type"));
                list.add(compte);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    public void showComptes() {
        ObservableList<Compte> list = getCompte();
        colcc.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("CodeCompte"));
        coldc.setCellValueFactory(new PropertyValueFactory<Compte, String>("DateCreation"));
        cols.setCellValueFactory(new PropertyValueFactory<Compte, Double>("Solde"));
        colc.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("client"));
        colt.setCellValueFactory(new PropertyValueFactory<Compte, String>("Type"));

        tvCompte.setItems(list);


    }

    public void insert() {
        String ch = tt.getSelectionModel().getSelectedItem().toString();
        String query = "INSERT INTO Compte  VALUES('" + tcc.getText() + "','" + tdc.getText() + "','" + ts.getText() +
                "','" + tc.getText() + "','" + ch + "')";
        //String value = tt.getSelectionModel().getSelectedItem().toString() ;
        executeQuery(query);
        System.out.println(query);
        showComptes();
    }

    public void update() {
        String qr = "UPDATE Compte SET  CodeCompte='" + tcc.getText() + "', DateCreation='" + tdc.getText() + "',Solde='" + ts.getText() + "',client='" + tc.getText() + "', Type= '" + tt.getSelectionModel().getSelectedItem().toString() + "' WHERE  CodeCompte= '" + tcc.getText() + "'";
        executeQuery(qr);
        showComptes();
    }

    public void delete() {
        String qr = "DELETE FROM Compte WHERE CodeCompte= " + tcc.getText() + "";
        executeQuery(qr);
        showComptes();
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

    public void clictv(MouseEvent mouseEvent) {
        Compte compte = (Compte) tvCompte.getSelectionModel().getSelectedItem();
        System.out.println(compte.getCodeCompte());
        System.out.println(compte.getDateCreation());
        System.out.println(compte.getSolde());
        System.out.println(compte.getClient());
        tcc.setText("" + compte.getCodeCompte());
        tdc.setText(compte.getDateCreation());
        ts.setText("" + compte.getSolde());
        tc.setText("" + compte.getClient());
        tt.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void Select(ActionEvent event) {
        String s = tt.getSelectionModel().getSelectedItem().toString();

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
