package com.company.model;

import com.company.exceptions.InvalidItemNameException;

public class Item {

    private String name;
    private String description;
    private String category;
    private double cost;
    private int stock;
    private int initialStock;
    private int timeProcess;

    // Item's constructor
    public Item(String name, String description, String category, double cost, int stock, int timeProcess) throws InvalidItemNameException {

        // To check there is no decimal numbers
        if (name.matches(".*\\d+.*"))
        {
            throw new InvalidItemNameException(name);
        }

        else
            this.name = name;

        this.description = description;
        this.category = category;
        this.cost = cost;
        this.stock = stock;
        this.timeProcess=timeProcess;
    }

    public boolean isBeverage() {
        return category.equals("Hot") || category.equals("Cold");
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getInitialStock() {return initialStock;}

    public double getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    public int getTimeProcess(){ return timeProcess;}


    // Setters
    void setInitialStock(int stock) {
        this.initialStock = stock;
    }

    void setStock(int variation) {
        this.stock+=variation;
    }

    // Used to print into the Terminal
    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "Description: " + description + "\n"
                + "Category: " + category + "\n"
                + "Cost: " + cost + "\n"
                + "Stock: " + stock + "\n\n";
    }

    // Used to display the the menu in the Final Report
    public String toStringReport() {
        return "Name:" + name + " ||"+ "\n"
                + " Stock:" + stock + "\n\n";
    }

}