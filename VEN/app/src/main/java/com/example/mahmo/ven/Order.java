package com.example.mahmo.ven;

import java.util.Date;

/**
 * Created by mahmo on 12/27/2017.
 */

public class Order implements Ven {
    private int id ,customerid,quntity ;
    private String orderDate , dueDate ;
    private boolean paied ;

    public Order(int id, int customerid,int quntity, String orderDate, String dueDate, boolean paied) {
        this.id = id;
        this.customerid = customerid;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.paied = paied;
        this.quntity =quntity ;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public boolean isPaied() {
        return paied;
    }

    public void setPaied(boolean paied) {
        this.paied = paied;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
