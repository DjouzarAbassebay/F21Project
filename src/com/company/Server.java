package com.company;

import java.util.List;

public class Server extends Thread{
    Order processingOrder;
    SharedObject sharedObject;
    Manager manager;
    int id;
    int processingSpeed = 1;


    public Server(int id,Manager manager, SharedObject sharedObject) {
        this.manager = manager;
        this.sharedObject = sharedObject;
        this.id = id;
    }

    public void run() {


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
                        time += item.getTimeProcess();
                    }
                    Thread.sleep(time*1000/processingSpeed);

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


    public Order getProcessingOrder() {
        return processingOrder;
    }
}
