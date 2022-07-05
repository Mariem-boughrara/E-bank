package com.example.demo;

public class Virement extends Operations {
    private int IdCompteExp;
    private int IdCompteRec;
    private Double Somme;

    public Virement(int id, String date, int idCompteExp, int idCompteRec, Double somme) {
        super(id, date);
        IdCompteExp = idCompteExp;
        IdCompteRec = idCompteRec;
        Somme = somme;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public int getIdCompteExp() {
        return IdCompteExp;
    }

    public void setIdCompteExp(int idCompteExp) {
        IdCompteExp = idCompteExp;
    }

    public int getIdCompteRec() {
        return IdCompteRec;
    }

    public void setIdCompteRec(int idCompteRec) {
        IdCompteRec = idCompteRec;
    }

    public Double getSomme() {
        return Somme;
    }

    public void setSomme(Double somme) {
        Somme = somme;
    }

    @Override
    public String toString() {
        return "Virement{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", IdCompteExp=" + IdCompteExp +
                ", IdCompteRec=" + IdCompteRec +
                ", Somme=" + Somme +
                '}';
    }
}
