package com.example.mahmo.ven;

/**
 * Created by mahmo on 12/27/2017.
 */

public class Product implements Ven {
    private String name ;
    private int price , quntity  ;

    public Product(String name ,int price, int quntity) {
        this.name = name;
        this.price = price;
        this.quntity = quntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }
}
