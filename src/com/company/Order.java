package com.company;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Item> items = new ArrayList<>();
    private int customerID;
    private String timestamp;
    private double discountPrice;
    private double initialPrice;

    public Order() {
    }

    public void applyDiscount() {

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

    public void calculatePrice() {
        initialPrice = 0;
        for (Item item : items) {
            initialPrice += item.getCost();
        }
    }

    private void updateOrder() {
        calculatePrice();
        applyDiscount();
    }


    public void addItem(Item item) {
        if(item.getStock()>0)
        {
            item.setStock(-1);
            items.add(item);
        }
        else
            System.out.println("Sorry, we don't have this item anymore.");

        updateOrder();
    }

    public void removeItem(int index) {
        items.get(index).setStock(1);
        items.remove(index);
        updateOrder();
    }

    void removeAllItem() {
        for (Item item : items) {
            item.setStock(1);
        }
        items.clear();
        updateOrder();
    }


    //Getters and setters
    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int value) {
        this.customerID = value;
    }

    String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    public double getInitialPrice() {
        return this.initialPrice;
    }

    void setInitialPrice(double value) {
        this.initialPrice += value;
    }

    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(double value) {
        this.discountPrice += value;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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