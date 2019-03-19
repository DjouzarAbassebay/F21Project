package com.company;

import java.io.IOException;

public class Server extends Thread{
    Order processingOrder;
    SharedObject sharedObject;
    Manager manager;
    private boolean baristaProcessing = false;
    private boolean baristaFinished = false;
    int id;
    int processingSpeed = 1;
    Log log;


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
        Log logger1 = null;
        try {
            logger1 = log.getInstance();
        } catch (IOException e) {
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
                    baristaProcessing = false;
                    baristaFinished = false;
                    logger1.log_order_server(processingOrder, id);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized boolean baristaTaking() {
        if(!baristaProcessing) {
            baristaProcessing = true;
            return true;
        }
        return false;
    }

    public void baristaFinished() {
        baristaFinished = true;
    }

    public Order getProcessingOrder() {return processingOrder;}

    public synchronized boolean getBaristaProcessing() {return baristaProcessing;}

    public int getServerId() {return id;}
}
