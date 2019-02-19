package com.company;


public class Item {

    private String name;
    private String description;
    private String category;
    private float cost;
    private int stock;
    private int initialStock;

    Item(String name, String description, String category, float cost, int stock) throws InvalidItemNameException {
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


    //Getters
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

    float getCost() {
        return cost;
    }

    int getStock() {
        return stock;
    }


    //Setters
    void setInitialStock(int stock) {
        this.initialStock = stock;

    }

    void setStock(int variation) {
        this.stock+=variation;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "Description: " + description + "\n"
                + "Category: " + category + "\n"
                + "Cost: " + cost + "\n"
                + "Stock: " + stock + "\n\n";
    }

    String toStringReport() {
        return "Name:" + name + " ||"+ "\n"
                + " Stock:" + stock + "\n\n";
    }

}