package com.example.demo;

public class Transaction extends Operations {
    private int c;
    private String type;
    private Double Somme;


    public Transaction(int id, String date, int c, String type, Double amount) {
        super(id, date);
        this.c = c;
        this.type = type;
        this.Somme = amount;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSomme() {
        return Somme;
    }

    public void setSomme(Double amount) {
        this.Somme = amount;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getDate() {
        return super.getDate();
    }
}
