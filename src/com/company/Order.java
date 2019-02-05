package com.company;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int customerID;
    private String timestamp;
    private float price;
    List<Item> items = new ArrayList<> ();

    public Order() {

    }


    public void applyDiscount() {
    }

    public void calculatePrice() {

    }

    public void addItem(Item item) {
        items.add(item);
        calculatePrice();
        applyDiscount();
    }

    public void removeItem(Item item) {
        items.remove(item); //Remove only the first occurence
    }

    //Getters
    int getCustomerID() {
        return this.customerID;
    }

    String getTimestamp() {
        return this.timestamp;
    }

    float getPrice() {
        return this.price;
    }


    //Setters
    void setCustomerID(int value) {
        this.customerID = value;
    }

    void setTimestamp(String value) {
        this.timestamp = value;
    }

    void setPrice(float value) {
        this.price += value;
    }

}
