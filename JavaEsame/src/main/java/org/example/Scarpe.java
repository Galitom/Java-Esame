package org.example;

public class Scarpe {
    private int id;
    private String name;
    private double price;
    private String genere;

    public Scarpe(int id, String name, double price, String genere) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.genere = genere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
