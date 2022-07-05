package com.example.demo;

public class Operations {
    protected int id;
    protected String date;

    public Operations(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operations{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }
}
