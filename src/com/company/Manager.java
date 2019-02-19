package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Manager {

    private String ordersPath = "orders.csv";
    private String menuPath = "menu.csv";
    private String reportPath = "reports";
    Map<String, Item> menu = new HashMap<>();
    private List<Order> orders = new ArrayList<>();

    Order currentOrder;
    private double income;


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
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(";");
                String category = words[0].split("_")[0];
                Item item = new Item(words[1], words[2], category, Float.parseFloat(words[3]), Integer.parseInt(words[4]));
                menu.put(words[0], item);
                item.setInitialStock(item.getStock());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initializeOrders() {
        try{
            FileInputStream fileInputStream = new FileInputStream(ordersPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            line = bufferedReader.readLine();

            while(line != null) {

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
                    order.addItem(item);
                    line = bufferedReader.readLine();

                    if (line!=null){
                        words = line.split(";");
                    }
                    else{
                        words[0] = "-1";
                    }
                }

                orders.add(order);
            }
        }
        catch (IOException e) {e.printStackTrace();}
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
        for(Order order : orders) {
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


    void calculateIncome() {


        for(Order order : orders) {
            System.out.println(order.toString());
            this.income += order.getDiscountPrice();

        }

        System.out.println("Final Income: "  +income + "       ");
    }

    private String calculateVariationClass(String id){

        StringBuilder variationStock = new StringBuilder();


        float variation = menu.get(id).getInitialStock() - menu.get(id).getStock();
        variationStock.append(id).append(" : ").append(variation);

        return variationStock.toString();

    }

    void generateReport() {
        BufferedWriter bw = null;
        try {

            //Specifying the file name and path
            File file = new File(".\\report.txt");

            // If the file does not exist, it will be created
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write("This is the report of the day");
            bw.newLine();
            bw.write("------------------------------");
            bw.newLine();
            bw.newLine();

            bw.write("Items in the menu: ");
            bw.newLine();

            for (String id: menu.keySet()){
                String value = menu.get(id).toString();
                bw.write(value);
                bw.newLine();
            }
            bw.newLine();
            bw.write("Items that have been sold: ");
            bw.newLine();
            for (String id : menu.keySet()) {
                bw.write(calculateVariationClass(id));
                bw.newLine();
            }


            bw.write("Total incomes for all orders: ");

            String SIncome = Double.toString(income);
            bw.write("£ "+SIncome);



            System.out.println("File written successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }



}
