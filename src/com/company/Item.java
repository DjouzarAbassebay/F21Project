package com.company;

public class Item {

    private String name;
    private String description;
    private String category;
    private double cost;
    private int stock;
    private int initialStock;

    // Item's constructor
    public Item(String name, String description, String category, double cost, int stock) throws InvalidItemNameException {

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
    }


    // Getters
    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    String getCategory() {
        return category;
    }

    int getInitialStock() {return initialStock;}

    double getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }


    // Setters
    public void setInitialStock(int stock) {
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
    String toStringReport() {
        return "Name:" + name + " ||"+ "\n"
                + " Stock:" + stock + "\n\n";
    }

}