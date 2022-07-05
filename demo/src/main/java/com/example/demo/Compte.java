package com.example.demo;

public class Compte {
    private int CodeCompte;
    private String DateCreation;
    private Double Solde;
    private int client;
    private String Type;

    public Compte(int codeCompte, String dateCreation, double solde, int client, String type) {
        super();
        CodeCompte = codeCompte;
        DateCreation = dateCreation;
        Solde = solde;
        this.client = client;
        this.Type = type;

    }

    public Compte() {
    }

    public Compte(int codeCompte, Double solde) {
        CodeCompte = codeCompte;
        Solde = solde;
    }

    public int getCodeCompte() {
        return CodeCompte;
    }

    public void setCodeCompte(int codeCompte) {
        CodeCompte = codeCompte;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(String dateCreation) {
        DateCreation = dateCreation;
    }

    public double getSolde() {
        return Solde;
    }

    public void setSolde(double solde) {
        Solde = solde;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getType() {
        return Type;
    }

    /*public void setType( Type) {
        this.Type = Type;
    }*/
    @Override
    public String toString() {
        return "Compte [CodeCompte=" + CodeCompte + ", DateCreation=" + DateCreation + ", Solde=" + Solde + ", client="
                + client + ", Type=" + Type + "]";
    }



}
