package com.company;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Item> items = new ArrayList<>();
    private int customerID;
    private String timestamp;
    private float discountPrice;
    private float intialPrice;

    public Order() {
    }


    public void applyDiscount() {

        int number_cold = 0;
        int number_sandwishes = 0;
        int number_hot = 0;
        discountPrice = intialPrice;

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
            discountPrice = (float) (0.9 * intialPrice);
            double newPrice = Math.round(discountPrice * 100.0) / 100.0;
            discountPrice = (float) newPrice;
        }

        if(number_cold>=1 && number_sandwishes>=1 && number_hot>=1) {
            discountPrice = (float) (0.8 * intialPrice);
            double newPrice = Math.round(discountPrice*100.0)/100.0;
            discountPrice = (float) newPrice;
        }

        if(number_cold>=2 && number_sandwishes>=2) {
            discountPrice = (float) (0.8 * discountPrice);
            double newPrice = Math.round(discountPrice * 100.0) / 100.0;
            discountPrice = (float) newPrice;
        }
    }

    public void calculatePrice() {
        intialPrice = 0;
        for (Item item : items) {
            intialPrice += item.getCost();
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

    float getIntialPrice() {
        return this.intialPrice;
    }

    void setIntialPrice(float value) {
        this.intialPrice += value;
    }

    float getDiscountPrice() {
        return this.discountPrice;
    }

    void setDiscountPrice(float value) {
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
                + "Item: " + items.size() + "\n\n";
    }

}