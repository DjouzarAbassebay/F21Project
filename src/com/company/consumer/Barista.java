package com.company.consumer;

import com.company.model.Item;
import com.company.model.Manager;

import java.util.List;

public class Barista extends Thread {

    Manager manager;
    private int id;
    private boolean running = true;

    public Barista(int id, Manager manager) {
        this.id = id;
        this.manager = manager;
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("New barista " + id);
        while(running) {
            Server server = waitForServer();
            System.out.println("Barista " + id + " take care of: " + server.getServerId() + "\n");
            completeOrder(server.getProcessingOrder().getItems());
            server.baristaFinished();
            System.out.println("Barista " + id + " finished order\n");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        manager.removeBarista(this);
    }

    public Server waitForServer() {
        List<Server> servers = manager.getServers();
        boolean occupied = false;
        int serverIndex = 0;
        Server server = servers.get(serverIndex);
        while(!occupied && serverIndex < servers.size()) {
            server = servers.get(serverIndex);
            if(server.getProcessingOrder() != null) {
                if (server.getProcessingOrder().containBeverage()) {
                    if(server.baristaTaking()) {
                        occupied = true;
                    }
                }
            }
            serverIndex++;
            if(serverIndex == servers.size())
                serverIndex = 0;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return server;
    }

    public void completeOrder(List<Item> items) {
        for (Item item : items) {
            if (item.isBeverage()) {
                try {
                    Thread.sleep(item.getTimeProcess()*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Barista " + id + " finished " + item + "\n");
            }
        }
    }

    public void stopBarista() {
        running = false;
    }


    public boolean getRunning()
    {
        return this.running;
    }
}
