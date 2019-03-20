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
    Log log;

    Log logger2 = null;
    private int processingSpeed = 1;


    public CsvProducer(SharedObject sharedObject, Map<String, Item> menu) throws IOException {
        this.sharedObject = sharedObject;
        this.menu = menu;
        try {
            logger2 = log.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CsvProducer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;

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
                    if(oldId != 0) {
                        addOrderToNormalOrdersSH(copyOrder(order));
                        logger2.log_order_new(order);
                    }
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
            addOrderToNormalOrdersSH(copyOrder(order));
            logger2.log_order_new(order);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrderToNormalOrdersSH(Order order) {

        Random r = new Random();
        int low = 1000;
        int high = 5000;
        int timeRandom = r.nextInt(high-low) + low;

        sharedObject.addOrderFromNormalOrders(order);
        System.out.println("This order has been correctly added to the shared object: \n"+order.toString()+"\n");
        try {
            Thread.sleep(timeRandom*2/processingSpeed);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public int getProcessingSpeed() {
        return processingSpeed;
    }

    public void setProcessingSpeed(int processingSpeed) {
        this.processingSpeed = processingSpeed;
    }

    public SharedObject getSharedObject()
    {
        return sharedObject;
    }
}
