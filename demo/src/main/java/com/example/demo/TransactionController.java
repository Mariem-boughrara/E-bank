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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class TransactionController implements Initializable {

    @FXML
    private Button btnvalider;

    @FXML
    private TableView<Transaction> tvtr;

    @FXML
    private Button idlogout;
    @FXML
    private TableColumn<Transaction, String> cold;

    @FXML
    private TableColumn<Transaction, Integer> colid;

    @FXML
    private TableColumn<Transaction, Integer> colidc;

    @FXML
    private TableColumn<Transaction, Double> cols;

    @FXML
    private TableColumn<Transaction, String> colt;
    @FXML
    private Label idl;
    @FXML
    private Label lbl;

    @FXML
    private TextField td;

    @FXML
    private TextField tid;

    @FXML
    private TextField tidc;

    @FXML
    private TextField tsom;

    @FXML
    private ComboBox<String> tty;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTransactions();
        ObservableList<String> list = FXCollections.observableArrayList("Retrait", "Dépôt");
        tty.setItems(list);


    }

    public ObservableList<Transaction> getTransaction() {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Transaction";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Transaction transaction;
            while (rs.next()) {
                transaction = new Transaction(rs.getInt("Idtransaction"), rs.getString("Date"), rs.getInt("IdCompte"), rs.getString("Type"), rs.getDouble("Somme"));
                list.add(transaction);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return list;
    }

    public void showTransactions() {
        ObservableList<Transaction> list = getTransaction();
        colid.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("Id"));
        cold.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        colt.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));
        colidc.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("c"));
        cols.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Somme"));
        tvtr.setItems(list);
    }

    public void clictv(MouseEvent mouseEvent) {
        Transaction transaction = (Transaction) tvtr.getSelectionModel().getSelectedItem();
        System.out.println(transaction.getId());
        System.out.println(transaction.getDate());
        System.out.println(transaction.getType());
        System.out.println(transaction.getC());
        System.out.println(transaction.getSomme());
        tid.setText("" + transaction.getId());
        td.setText(transaction.getDate());
        tidc.setText("" + transaction.getC());
        tty.getSelectionModel().getSelectedItem();
        tsom.setText("" + transaction.getSomme());
    }

    public Compte getComptetr() {
        ObservableList<Compte> list = FXCollections.observableArrayList();
        Connection con = getconnection();
        String query = "SELECT * FROM Compte ";
        Statement st;
        ResultSet rs;

        Compte compte = new Compte(0, -1.0);
        Compte cpte = new Compte();
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

        Stream<Compte> cptes = list.stream().filter(item -> item.getCodeCompte() == parseInt(tidc.getText()));
        cpte = cptes.findFirst().get();
        return cpte;


    }

    public void HandleOnActionval(ActionEvent e) throws IOException {
        if ((tid.getText().isEmpty() && td.getText().isEmpty() && tidc.getText().isEmpty() && tsom.getText().isEmpty()) || (tid.getText().isEmpty() || td.getText().isEmpty() || tidc.getText().isEmpty() || tsom.getText().isEmpty())) {
            lbl.setText("Veuillez remplir tous les champs s'il vous plait !! ");
        }
        Compte c = getComptetr();

        if (e.getSource() == btnvalider && tty.getSelectionModel().getSelectedItem().toString() == "Dépôt") {
            insert();
            c.setSolde(c.getSolde() + Double.parseDouble(tsom.getText()));
            System.out.println(c.getSolde());

            String qr = "UPDATE Compte SET  Solde='" + c.getSolde() + "' WHERE  CodeCompte= '" + c.getCodeCompte() + "'";
            executeQuery(qr);
            lbl.setText("Dépôt effectué ! ");


        } else {
            if (e.getSource() == btnvalider && tty.getSelectionModel().getSelectedItem().toString() == "Retrait" && c.getSolde() > Double.parseDouble(tsom.getText())) {
                insert();
                c.setSolde(c.getSolde() - Double.parseDouble(tsom.getText()));
                System.out.println(c.getSolde());
                String qr = "UPDATE Compte SET  Solde='" + c.getSolde() + "' WHERE  CodeCompte= '" + c.getCodeCompte() + "'";
                executeQuery(qr);
                lbl.setText("Retrait effectué ! ");

            } else
                lbl.setText("Votre solde est insuffisant ");
        }


    }

    public void insert() {
        String ch = tty.getSelectionModel().getSelectedItem().toString();

        String query = "INSERT INTO Transaction  VALUES('" + tid.getText() + "','" + td.getText() + "','" + ch +
                "','" + tidc.getText() + "','" + tsom.getText() + "')";
        //String value = tt.getSelectionModel().getSelectedItem().toString() ;
        executeQuery(query);
        System.out.println(query);
        showTransactions();

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

    @FXML
    void logout(ActionEvent e) throws IOException {
        Stage stage =(Stage) idlogout.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setScene(new Scene(root) );
        primaryStage.show();
        /*HelloApplication m = new HelloApplication();
        m.ChangeScene("Login.fxml");*/
    }

    @FXML
    public void select(ActionEvent event) {
        String s = tty.getSelectionModel().getSelectedItem().toString();


    }
}
