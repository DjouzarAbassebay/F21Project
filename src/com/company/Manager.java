package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    Map<String, Item> menu = new HashMap<>();
    private List<Order> orders = new ArrayList<>();

    Order currentOrder;

    //Constructor
    public Manager() {

        newCurrentOrder();

        initializeMenu();
        initializeOrders();

        viewMenu();
        viewOrders();
    }

    // Method to create a copy of an order
    public static Order copyOrder(Order order) {
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

    // Method to initialize the menu from the CSV file : menu.csv
    private void initializeMenu() {

        try {
            Item item;
            String message;

            // Read the CSV file
            String menuPath = "menu.csv";
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            // Stop if it is not the last line of the CSV
            while ((line = bufferedReader.readLine()) != null) {

                String[] words = line.split(";");

                try {
                    if(!words[0].matches("((Hot|Cold|Sandwiches|Pastry)_\\d{3})"))
                    {
                        throw new InvalidItemIdException(words[0]);
                    }
                    String category = words[0].split("_")[0];
                    item = new Item(words[1], words[2], category, Float.parseFloat(words[3]), Integer.parseInt(words[4]));
                    menu.put(words[0], item);
                    item.setInitialStock(item.getStock());
                } catch (InvalidItemNameException | InvalidItemIdException e) {
                    message = e.getMessage() + "\n Item not added: "+words[1];
                    System.out.println(message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Method that can increase stock when closing the window
    public void addStock(int item_nb, int stock_nb) {
        try {
            String line;
            String menuPath = "menu.csv";
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String[] lines = new String[menu.size()];

            int j = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lines[j] = line;
                j++;
            }
            OutputStream fileOutputStream = new FileOutputStream(menuPath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputStreamWriter);

            for(int i = 0 ; i<menu.size() ; i++) {
                String[] words = lines[i].split(";");
                if (i == item_nb) {
                    int stock = Integer.parseInt(words[4]) + stock_nb;
                    String stock_string = Integer.toString(stock);
                    words[4] = stock_string;
                    lines[i] = words[0] + ";" + words[1] + ";" + words[2] + ";" + words[3] + ";" + words[4] ;
                    System.out.println(words[0] + ";" + words[1] + ";" + words[2] + ";" + words[3] + ";" + words[4]);
                }

            }

            for(int k = 0 ; k<menu.size() ; k++) {
                System.out.println(lines[k]);
                bufferedwriter.write(lines[k]);
                bufferedwriter.newLine();
            }
            bufferedwriter.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to initialize the list of orders  from the CSV file : orders.csv
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
                    } catch (NullPointerException e) {
                        System.out.println("Invalid Item: " + words[2]);
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

    // method to create a current order
    private void newCurrentOrder() {
        currentOrder = new Order();
    }

    // method to add the current order into the list of orders
    public void validateCurrentOrder() {
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

    // method to display the menu in the terminal
    private void viewMenu() {
        for (String id : menu.keySet()) {
            String value = menu.get(id).toString();
            System.out.println("ID: " + id);
            System.out.println(value);
        }
    }

    // method to display the orders from the csv file in the terminal
    private void viewOrders() {
        System.out.println("Orders List");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // method to display the items in each order in the terminal
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

    //getters
    public List<Order> getOrders() {
        return orders;
    }

    //setters
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }
}