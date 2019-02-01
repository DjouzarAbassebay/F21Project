package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    private static String ordersPath = "orders.csv";
    private static String menuPath = "menu.csv";
    private static String reportPath = "reports";
    public static Map<Object, Item> menu = new HashMap<> ();
    public static List<Order> orders = new ArrayList<> ();


    public Manager() {

    }


    //Methods
    public void initializeOrders() {
    }

    public void initializeMenu() {
    }

    public void generateReport() {
    }

    public void newOrder() {
    }

    public void cancelOrder() {
    }

    public void validateOrder() {
    }

    public void updateStock() {
    }


    //Getters
    static String getOrdersPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return ordersPath;
    }

    static String getMenuPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return menuPath;
    }

    static String getReportPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return reportPath;
    }


    //Setters
    static void setMenuPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        menuPath = value;
    }

    static void setOrdersPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        ordersPath = value;
    }

    static void setReportPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        reportPath = value;
    }

}
