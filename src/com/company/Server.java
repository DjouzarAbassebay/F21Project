package com.company;

import java.util.List;

public class Server extends Thread{
    Order processingOrder;
    SharedObject sharedObject;
    Manager manager;
    int id;


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
                try {
                    System.out.println("id: "+id+"\n"+processingOrder.toString());
                    int time=0;
                    for(Item item:processingOrder.getItems()){
                        time += item.getTimeProcess();
                    }
                    Thread.sleep(time*1000);

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
}
