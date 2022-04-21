package model;
import java.awt.*;

public class Product {
    private int id;
    private String name;
    private int stock;
    private float price;

    public Product(){

    }

    public Product(int id, String name, int stock,float price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public void setName(String nume) {
        this.name = nume;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float pret) {
        this.price = pret;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stoc) {
        this.stock = stoc;
    }
}
