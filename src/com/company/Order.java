package com.company;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int customerID;
    private int timestamp;
    private float price;
    public List<Item> items = new ArrayList<Item> ();

    public Order() {

    }


    public void applyDiscount() {
    }

    public void addItem(Item item) {
    }

    public void removeItem(Item item) {
    }


    //Getters
    int getCustomerID() {
        return this.customerID;
    }

    int getTimestamp() {
        return this.timestamp;
    }

    float getPrice() {
        return this.price;
    }


    //Setters
    void setCustomerID(int value) {
        this.customerID = value;
    }

    void setTimestamp(int value) {
        this.timestamp = value;
    }

    void setPrice(float value) {
        this.price = value;
    }

}
