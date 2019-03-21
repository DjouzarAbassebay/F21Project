package com.company.consumer;

import com.company.model.Item;
import com.company.model.Manager;
import com.company.outputs.Log;


import java.io.IOException;

import java.util.List;

public class Barista extends Thread {

    private Manager manager;
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
        Log logger1 = null;
        try {
            logger1 = Log.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(running) {
            Server server = waitForServer();
            System.out.println("Barista " + id + " take care of: " + server.getServerId() + "\n");
            completeOrder(server.getProcessingOrder().getItems());
            server.baristaFinished();
            System.out.println("Barista " + id + " finished order\n");

            try {
                assert logger1 != null;
                logger1.log_barista(id, server.getServerId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        manager.removeBarista(this);
    }

    private Server waitForServer() {
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

    private void completeOrder(List<Item> items) {
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
