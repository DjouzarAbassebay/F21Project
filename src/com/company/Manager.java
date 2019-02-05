package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    private static String ordersPath = "orders.csv";
    private static String menuPath = "menu.csv";
    private static String reportPath = "reports";
    public static Map<String, Item> menu = new HashMap<> ();
    public static List<Order> orders = new ArrayList<> ();

    static Order currentOrder;


    public Manager() {
        initializeMenu();
        initializeOrders();

        viewMenu();
    }

    //Methods
    public void initializeOrders() {
    }

    public void initializeMenu() {
        try{
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(";");
                String category = words[0].split("_")[0];
                Item item = new Item(words[1],category,Float.parseFloat(words[2]),Integer.parseInt(words[3]));
                menu.put(words[0], item);
            }
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public void generateReport() {
    }

    public void newOrder() {
        currentOrder = new Order();
    }

    public void cancelOrder() {
        for(Item item: currentOrder.items)
        {
            item.setStock(1);
        }
    }

    public void validateOrder() {
        Order lastOrder = orders.get(orders.size()-1);
        int customerID = lastOrder.getCustomerID() + 1;
        currentOrder.setCustomerID(customerID);
        currentOrder.setTimestamp(java.time.LocalDateTime.now().toString());
        orders.add(currentOrder);
    }

    public boolean updateOrder(){
        if(currentOrder.items.get(currentOrder.items.size()-1).getStock()>0) {
            currentOrder.setPrice(currentOrder.items.size()-1);
            currentOrder.items.get(currentOrder.items.size()-1).setStock(-1);
            return true;
        }

        System.out.println("Item out of stock");
        return false;
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


    public void viewMenu() {
        for (String id: menu.keySet()){
            String value = menu.get(id).toString();
            System.out.println("ID: " + id);
            System.out.println(value);
        }
    }

}
