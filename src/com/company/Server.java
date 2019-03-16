package com.company;

import java.io.IOException;
import java.util.List;

public class Server extends Thread{
    Order processingOrder;
    Manager manager;
    int id;
    Log log;


    public Server(int id,Manager manager) {
        this.manager = manager;
        this.id = id;
    }

    public void run() {
        try {
            Log logger1 = log.getInstance();

        while((processingOrder=manager.getNextOrder())!=null)
        {

                System.out.println("id: "+id+"\n"+processingOrder.toString());
                int time=0;
                for(Item item:processingOrder.getItems()){
                    time += item.getTimeProcess();
                }
                Thread.sleep(time*1000);

                manager.addProcessedOrder(Manager.copyOrder(processingOrder));
                logger1.log_order(processingOrder);
            }

        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
