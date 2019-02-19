package com.company;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Item> items = new ArrayList<>();
    private int customerID;
    private String timestamp;
    private float price;

    public Order() {
    }


    public void applyDiscount() {

        int number_cold = 0;
        int number_sandwishes = 0;
        int number_hot = 0;

        for (int i = 0; i < items.size(); i++) {

            String category = items.get(i).getCategory();

            if (category.equals("Cold"))
                number_cold += 1;

            if (category.equals("Hot"))
                number_hot += 1;

            if (category.equals("Sandwishes"))
                number_sandwishes += 1;
        }

        if (number_hot >= 4) {
            price = (float) (0.9 * price);
            double newPrice = Math.round(price * 100.0) / 100.0;
            price = (float) newPrice;
        }

        if(number_cold>=1 && number_sandwishes>=1 && number_hot>=1) {
            price = (float) (0.8 * price);
            double newPrice = Math.round(price*100.0)/100.0;
            price = (float) newPrice;
        }

        if(number_cold>=2 && number_sandwishes>=2) {
            price = (float) (0.8 * price);
            double newPrice = Math.round(price * 100.0) / 100.0;
            price = (float) newPrice;
        }
    }

    public void calculatePrice() {
        price = 0;
        for (Item item : items) {
            price += item.getCost();
        }
    }

    public void addItem(Item item) {
        if(item.getStock()>0)
        {
            item.setStock(-1);
            items.add(item);
        }
        else
            System.out.println("Sorry, we don't have this item anymore.");

    }

    public void updateOrder() {

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

    float getPrice() {
        return this.price;
    }

    void setPrice(float value) {
        this.price += value;
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
                + "Price: " + price + "\n"
                + "Item: " + items.size() + "\n\n";
    }

}