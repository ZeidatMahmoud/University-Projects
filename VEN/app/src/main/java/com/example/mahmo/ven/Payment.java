package com.example.mahmo.ven;

import java.util.Date;

/**
 * Created by mahmo on 12/27/2017.
 */

public class Payment implements Ven{
    private String customerid ,amountPaied ,date;

    public Payment(String customerid, String amountPaied, String date) {
        this.customerid = customerid;
        this.amountPaied = amountPaied;
        this.date = date;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getAmountPaied() {
        return amountPaied;
    }

    public void setAmountPaied(String amountPaied) {
        this.amountPaied = amountPaied;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
