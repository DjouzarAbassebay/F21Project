package com.company;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;

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

                Random r = new Random();
                int low = 1000;
                int high = 5000;
                int timeRandom = r.nextInt(high-low) + low;

                sharedObject.addOrder(order);
                System.out.println("This order has been correctly added to the shared object: \n"+order.toString()+"\n");
                Thread.sleep(timeRandom*2);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
