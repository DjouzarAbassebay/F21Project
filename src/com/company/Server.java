package com.company;

import java.util.List;

public class Server extends Thread{
    Order processingOrder;
    SharedObject sharedObject;
    Manager manager;
    private boolean baristaProcessing = false;
    private boolean baristaFinished = false;
    int id;
    int processingSpeed = 1;


    public Server(int id,Manager manager, SharedObject sharedObject) {
        this.manager = manager;
        this.sharedObject = sharedObject;
        this.id = id;
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true)
        {
            if((processingOrder=sharedObject.getNextOrder())!=null)
            {
                // Remove the processing order to this associated order list
                if(sharedObject.getPriorityOrders().contains(processingOrder)){
                    sharedObject.removeOrderToPrioritylOrders(processingOrder);
                } else {
                    sharedObject.removeOrderToNormalOrders(processingOrder);
                }

                try {
                    System.out.println("Order Processing...");
                    System.out.println("Server id: "+id+"\n"+processingOrder.toString());
                    baristaProcessing = false;
                    baristaFinished = false;
                    int time=0;
                    for(Item item:processingOrder.getItems()){
                        if(!item.isBeverage())
                            time += item.getTimeProcess();
                    }
                    Thread.sleep(time*1000/processingSpeed);
                    while(!baristaFinished) {
                        Thread.sleep(100);
                    }
                    manager.addProcessedOrder(Manager.copyOrder(processingOrder));

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
    }

    public void baristaTaking() {
        baristaProcessing = true;
    }

    public void baristaFinished() {
        baristaFinished = true;
    }

    public Order getProcessingOrder() {return processingOrder;}

    public synchronized boolean getBaristaProcessing() {return baristaProcessing;}
}
