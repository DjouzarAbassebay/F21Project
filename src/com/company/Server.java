package com.company;

import java.util.List;

public class Server extends Thread{
    Order processingOrder;
    SharedObject sharedObject;
    Manager manager;
    int id;
    private int processingSpeed = 1;
    private boolean running = true;


    public Server(int id,Manager manager, SharedObject sharedObject) {
        this.manager = manager;
        this.sharedObject = sharedObject;
        this.id = id;
    }

    public void run() {
        while(running)
        {
            if((processingOrder=sharedObject.getNextOrder())!=null)
            {
                // Remove the processing order to this associated order list
                if(sharedObject.getPriorityOrders().contains(processingOrder)){
                    sharedObject.removeOrderFromPriorityOrders(processingOrder);
                } else {
                    sharedObject.removeOrderFromNormalOrders(processingOrder);
                }

                try {
                    System.out.println("Order Processing...");
                    System.out.println("Server id: "+id+"\n"+processingOrder.toString());
                    int time=0;
                    for(Item item:processingOrder.getItems()){
                        time += item.getTimeProcess();
                    }
                    Thread.sleep(time*1000/processingSpeed);

                    Order copiedOrder = Manager.copyOrder(processingOrder);
                    processingOrder = null;
                    manager.addProcessedOrder(Manager.copyOrder(copiedOrder));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());

                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        manager.removeServer(this);
    }

    public void stopServer() {
        running = false;
    }

    public Order getProcessingOrder() {
        return processingOrder;
    }

    public int getProcessingSpeed() {
        return processingSpeed;
    }

    public void setProcessingSpeed(int processingSpeed) {
        this.processingSpeed = processingSpeed;
    }
}
