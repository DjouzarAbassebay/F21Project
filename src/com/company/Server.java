package com.company;

import java.util.List;

public class Server extends Thread{
    Order processingOrder;
    Manager manager;
    int id;
    int processingSpeed = 1;


    public Server(int id,Manager manager) {
        this.manager = manager;
        this.id = id;
    }

    public void run() {
        while((processingOrder=manager.getNextOrder())!=null)
        {
            try {
                System.out.println("id: "+id+"\n"+processingOrder.toString());
                int time=0;
                for(Item item:processingOrder.getItems()){
                    time += item.getTimeProcess();
                }
                Thread.sleep(time*1000/processingSpeed);

                manager.addProcessedOrder(Manager.copyOrder(processingOrder));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());

            }
        }
    }
}
