package com.example.demo;

public class Employe {
    private int CodeEmploye;
    private String NomEmploye;
    private String PrenomEmploye;
    private String Adresse;

    public Employe(int codeEmploye, String nomEmploye, String prenomEmploye, String adresse) {

        this.CodeEmploye = codeEmploye;
        this.NomEmploye = nomEmploye;
        this.PrenomEmploye = prenomEmploye;
        this.Adresse = adresse;
    }

    public int getCodeEmploye() {
        return CodeEmploye;
    }

    public void setCodeEmploye(int codeEmploye) {
        CodeEmploye = codeEmploye;
    }

    public String getNomEmploye() {
        return NomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        NomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return PrenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        PrenomEmploye = prenomEmploye;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    @Override
    public String toString() {
        return "Employe [CodeEmploye=" + CodeEmploye + ", NomEmploye=" + NomEmploye + ", PrenomEmploye=" + PrenomEmploye
                + ", Adresse=" + Adresse + "]";
    }

}
