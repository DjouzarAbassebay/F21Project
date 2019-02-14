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
        price = 0;
        for(Item item : items) {
            price += item.getCost();
        }
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
    public int getCustomerID() {
        return this.customerID;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public float getPrice() {
        return this.price;
    }
    public List<Item> getItems() {
        return items;
    }


    //Setters
    public void setCustomerID(int value) {
        this.customerID = value;
    }

    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    public void setPrice(float value) {
        this.price += value;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "CustomerId " +  customerID + "\n"
                + "Timestamp " + timestamp + "\n"
                + "Price: " + price+ "\n"
                + "Item: " + items.size() + "\n\n";
    }

}