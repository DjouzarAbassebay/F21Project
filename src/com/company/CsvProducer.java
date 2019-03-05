package com.company;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;

import static com.company.Manager.copyOrder;

public class CsvProducer extends  Thread{

    private SharedObject sharedObject;
    Map<String, Item> menu ;

    public CsvProducer(SharedObject sharedObject, Map<String, Item> menu) {
        this.sharedObject = sharedObject;
        this.menu = menu;
    }


    // Method to initialize the list of orders  from the CSV file : orders.csv
    public void run() {
        try {
            String ordersPath = "orders.csv";
            FileInputStream fileInputStream = new FileInputStream(ordersPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            String[] words;
            Order order = new Order();
            int oldId = 0, newId;

            while ((line=bufferedReader.readLine()) != null) {
                words = line.split(";");
                newId = Integer.parseInt(words[0]);

                if(oldId != newId) {
                    if(oldId != 0) {addOrderToSharedObject(copyOrder(order));}
                    order = new Order();
                    oldId = newId;
                    order.setCustomerID(newId);
                    order.setTimestamp(words[1]);
                    order.setName(words[2]);
                }

                Item item = menu.get(words[3]);
                try {
                    order.addItem(item);
                } catch (NullPointerException e) {
                    System.out.println("Invalid Item: " + words[3]);
                }
            }
            addOrderToSharedObject(copyOrder(order));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrderToSharedObject(Order order) {
        Random r = new Random();
        int low = 1000;
        int high = 5000;
        int timeRandom = r.nextInt(high-low) + low;

        sharedObject.addOrder(order);
        System.out.println("This order has been correctly added to the shared object: \n"+order.toString()+"\n");
        try {
            Thread.sleep(timeRandom*2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
