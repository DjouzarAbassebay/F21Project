package com.company;

import java.io.*;
import java.util.*;

public class Manager {
    Map<String, Item> menu = new HashMap<>();

    private List<Order> processedOrders = new ArrayList<>();
    List<Server> servers = new ArrayList<>();
    SharedObject sharedObject;

    Order currentOrder;

    //Constructor
    public Manager(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
        initializeMenu();
        viewMenu();
    }

    // Method to create a deep copy of an order
    public static Order copyOrder(Order order) {
        Order copy = new Order();
        copy.setCustomerID(order.getCustomerID());
        copy.setTimestamp(order.getTimestamp());
        copy.setName(order.getName());
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
                    item = new Item(words[1], words[2], category, Float.parseFloat(words[3]), Integer.parseInt(words[4]), Integer.parseInt(words[5]));
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

    public void addServers(int nbServers) {
        int serversListSize = servers.size();
        for (int i = 0; i < nbServers - serversListSize; i++) {
            System.out.println("Manager Servers Size : " + serversListSize);

            // Add server(s) if the maximum number of servers is not reached !
            Server server = new Server(serversListSize+i, this, sharedObject);
            servers.add(server);
            // When a new server is added, start this thread
            server.start();

        }
        System.out.println("Servers List Size : " + servers.size());
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


    void addProcessedOrder(Order order) {
        processedOrders.add(order);
    }

    public Map<String, Item> getMenu() {
        return menu;
    }



}