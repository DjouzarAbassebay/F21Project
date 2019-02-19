package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Manager {

    private  String ordersPath = "orders.csv";
    private  String menuPath = "menu.csv";
    private  String reportPath = "reports";
    public  Map<String, Item> menu = new HashMap<>();
    public  List<Order> orders = new ArrayList<>();

    Order currentOrder;
    private float income;


    public Manager() {

        newCurrentOrder();

        initializeMenu();
        initializeOrders();

        viewMenu();
        viewOrders();

        generateReport();
    }

    //Methods


    public void initializeMenu() {
        try {
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(";");
                String category = words[0].split("_")[0];
                Item item = new Item(words[1], category, Float.parseFloat(words[2]), Integer.parseInt(words[3]));
                menu.put(words[0], item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initializeOrders() {
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
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public void generateReport() {
        BufferedWriter bw = null;
        try {

            //Specifying the file name and path
            File file = new File("C:./report.txt");

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
                System.out.println("ID: " + id);
                bw.write(value);

                bw.newLine();

            }

            bw.write("Items that have been sold: ");
            bw.newLine();


            bw.write("Total incomes for all orders: ");

            String SIncome = Float.toString( income);
            bw.write(SIncome);

            System.out.println("File written Successfully");

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

    public void newCurrentOrder() {
        currentOrder = new Order();
    }

    public void cancelCurrentOrder() {
        for (Item item : currentOrder.items) {
            item.setStock(1);
        }
    }

    public  void validateCurrentOrder() {

        if (orders.isEmpty()) {

            System.out.println("if");

            int customerID = 1;
            currentOrder.setCustomerID(customerID);
            currentOrder.setTimestamp(java.time.LocalDateTime.now().toString());


            orders.add(copyOrder(currentOrder));


        } else {

            System.out.println("else");

            Order lastOrder = orders.get(orders.size() - 1);
            int customerID = lastOrder.getCustomerID() + 1;
            currentOrder.setCustomerID(customerID);
            currentOrder.setTimestamp(java.time.LocalDateTime.now().toString());
            orders.add(copyOrder(currentOrder));



        }
        displayOrders();
        newCurrentOrder();
    }

    public boolean updateCurrentOrder() {
        if (currentOrder.items.get(currentOrder.items.size() - 1).getStock() > 0) {
            currentOrder.setPrice(currentOrder.items.size() - 1);
            currentOrder.items.get(currentOrder.items.size() - 1).setStock(-1);
            return true;
        }

        System.out.println("Item out of stock");
        return false;
    }

    public  void displayOrders() {

        System.out.println("Orders List");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).getCustomerID());
            if (orders.get(i).items.isEmpty())
                System.out.println("Order empty !");
            else {
                for (int j = 0; j < orders.get(i).items.size(); j++) {
                    System.out.println(orders.get(i).items.get(j));
                }
            }
        }
    }

    public void calculateIncome() {

        for(Order order : orders) {
            this.income += order.getPrice();
        }

        System.out.println("Final Income: "  +income + "       ");
    }


    //Getters
     String getOrdersPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return ordersPath;
    }

     String getMenuPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return menuPath;
    }

     String getReportPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return reportPath;
    }

     float getIncome(){

        return income;
     }


    //Setters
     void setMenuPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        menuPath = value;
    }


     void setOrdersPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        ordersPath = value;
    }

     void setReportPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        reportPath = value;
    }


    public void viewMenu() {
        for (String id : menu.keySet()) {
            String value = menu.get(id).toString();
            System.out.println("ID: " + id);
            System.out.println(value);
        }
    }


    public Order copyOrder(Order order)
    {

        Order copy = new Order();
        copy.setCustomerID(order.getCustomerID());
        String timestamp = order.getTimestamp();
        copy.setTimestamp(new String(timestamp));
        copy.setPrice(order.getPrice());
        ArrayList newItems = (ArrayList) order.getItems();
        copy.setItems((List) newItems.clone());

        return copy;
    }

    public void viewOrders() {
        for (int i=0; i< orders.size(); i++){
            Order order = orders.get(i);
            System.out.println(order);
        }
    }

}
