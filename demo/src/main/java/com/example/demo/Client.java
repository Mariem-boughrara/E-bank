package com.example.demo;

public class Client {
    private int CodeClient;
    private String NomClient;
    private String PrenomClient;
    private String AdresseClient;

    public Client(int codeClient, String nomClient, String prenomClient, String adresseClient) {

        this.CodeClient = codeClient;
        this.NomClient = nomClient;
        this.PrenomClient = prenomClient;
        this.AdresseClient = adresseClient;
    }

    public int getCodeClient() {
        return CodeClient;
    }

    public void setCodeClient(int codeClient) {
        CodeClient = codeClient;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }

    public String getPrenomClient() {
        return PrenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        PrenomClient = prenomClient;
    }

    public String getAdresseClient() {
        return AdresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        AdresseClient = adresseClient;
    }

    @Override
    public String toString() {
        return "Client [CodeClient=" + CodeClient + ", NomClient=" + NomClient + ", PrenomClient=" + PrenomClient
                + ", AdresseClient=" + AdresseClient + "]";
    }

    ;
}
