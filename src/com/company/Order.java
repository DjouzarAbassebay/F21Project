package com.company;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Item> items = new ArrayList<>();
    private int customerID;
    private String timestamp;
    private double discountPrice;
    private double initialPrice;

    Order() {
    }

    private void applyDiscount() {

        int number_cold = 0;
        int number_sandwiches = 0;
        int number_hot = 0;
        int number_pastry = 0;
        discountPrice = initialPrice;

        for (Item item : items) {

            String category = item.getCategory();

            if (category.equals("Cold"))
                number_cold += 1;

            if (category.equals("Hot"))
                number_hot += 1;

            if (category.equals("Sandwiches"))
                number_sandwiches += 1;

            if (category.equals("Pastry"))
                number_pastry += 1;
        }

        if(number_cold==1 && number_sandwiches==1 && number_hot==0 && number_pastry==1) {
            discountPrice = 7.0;
        }
        else if(number_cold==0 && number_sandwiches==3 && number_hot==0 && number_pastry==0) {
            discountPrice = 13;
        }
        else if(number_cold==0 && number_sandwiches==0 && number_hot==0 && number_pastry==3) {
            discountPrice = 5;
        }
        else if(number_cold==0 && number_sandwiches==0 && number_hot>=4 && number_pastry==0) {
            discountPrice = initialPrice*0.9;
        }
    }

    private void calculatePrice() {
        initialPrice = 0;
        for (Item item : items) {
            initialPrice += item.getCost();
        }
    }

    void addItem(Item item) {
        if(item.getStock()>0)
        {
            item.setStock(-1);
            items.add(item);
        }
        else
            System.out.println("Sorry, we don't have this item anymore.");

    }

    void updateOrder() {
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

    //Setters
    void setCustomerID(int value) {
        this.customerID = value;
    }

    String getTimestamp() {
        return this.timestamp;
    }

    void setTimestamp(String value) {
        this.timestamp = value;
    }

    double getInitialPrice() {
        return this.initialPrice;
    }

    void setInitialPrice(double value) {
        this.initialPrice += value;
    }

    double getDiscountPrice() {
        return this.discountPrice;
    }

    void setDiscountPrice(double value) {
        this.discountPrice += value;
    }

    List<Item> getItems() {
        return items;
    }

    void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "CustomerId " + customerID + "\n"
                + "Timestamp " + timestamp + "\n"
                + "Price: " + discountPrice + "\n"
                + "Number of Items: " + items.size() + "\n\n";
    }
}