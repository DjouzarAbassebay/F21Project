package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Manager {
    Map<String, Item> menu = new HashMap<>();
    private List<Order> orders = new ArrayList<>();

    Order currentOrder;


    Manager() {

        newCurrentOrder();

        initializeMenu();
        initializeOrders();

        viewMenu();
        viewOrders();


    }


    //Methods

    private void initializeMenu() {


        try {
            Item item;
            String message = "";

            String menuPath = "menu.csv";
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(";");
                String category = words[0].split("_")[0];
                try {
                    item = new Item(words[1], words[2], category, Float.parseFloat(words[3]), Integer.parseInt(words[4]));
                    menu.put(words[0], item);
                    item.setInitialStock(item.getStock());
                } catch (InvalidItemNameException e) {
                    message = e.getMessage() + "\n Item not added: "+words[1];
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeOrders() {
        try {
            String ordersPath = "orders.csv";
            FileInputStream fileInputStream = new FileInputStream(ordersPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            line = bufferedReader.readLine();

            while (line != null) {

                String[] words = line.split(";");

                Order order = new Order();
                order.setCustomerID(Integer.parseInt(words[0]));
                order.setTimestamp(words[1]);
                Item item = menu.get(words[2]);
                order.addItem(item);

                line = bufferedReader.readLine();
                words = line.split(";");

                while (Integer.parseInt(words[0]) == order.getCustomerID()) {
                    item = menu.get(words[2]);
                    try {
                        order.addItem(item);
                    }
                    catch(NullPointerException e){
                        System.out.println("Invalid Item: "+words[2]);

                    }
                    line = bufferedReader.readLine();

                    if (line != null) {
                        words = line.split(";");
                    } else {
                        words[0] = "-1";
                    }
                }

                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void newCurrentOrder() {
        currentOrder = new Order();
    }

    void validateCurrentOrder() {
        if (orders.isEmpty()) {
            int customerID = 1;
            currentOrder.setCustomerID(customerID);
            currentOrder.setTimestamp(java.time.LocalDateTime.now().toString());
            orders.add(copyOrder(currentOrder));
        } else {
            Order lastOrder = orders.get(orders.size() - 1);
            int customerID = lastOrder.getCustomerID() + 1;
            currentOrder.setCustomerID(customerID);
            currentOrder.setTimestamp(java.time.LocalDateTime.now().toString());
            orders.add(copyOrder(currentOrder));
        }
        displayOrders();
        newCurrentOrder();
    }


    private void viewMenu() {
        for (String id : menu.keySet()) {
            String value = menu.get(id).toString();
            System.out.println("ID: " + id);
            System.out.println(value);
        }
    }

    private void viewOrders() {
        System.out.println("Orders List");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void displayOrders() {

        for (Order order : orders) {
            System.out.println(order.getCustomerID());
            if (order.items.isEmpty())
                System.out.println("Order empty !");
            else {
                for (int j = 0; j < order.items.size(); j++) {
                    System.out.println(order.items.get(j));
                }
            }
        }
    }

    private Order copyOrder(Order order) {
        Order copy = new Order();
        copy.setCustomerID(order.getCustomerID());
        String timestamp = order.getTimestamp();
        copy.setTimestamp(timestamp);
        copy.setDiscountPrice(order.getDiscountPrice());
        copy.setInitialPrice(order.getInitialPrice());
        ArrayList newItems = (ArrayList) order.getItems();
        copy.setItems((List) newItems.clone());

        return copy;
    }


    List<Order> getOrders() {
        return orders;
    }

}