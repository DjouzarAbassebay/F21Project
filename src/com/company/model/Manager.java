package com.company.model;

import com.company.consumer.Barista;
import com.company.consumer.Server;
import com.company.exceptions.InvalidItemIdException;
import com.company.exceptions.InvalidItemNameException;
import com.company.interfaces.Observer;
import com.company.interfaces.Subject;

import java.io.*;
import java.util.*;


public class Manager implements Subject {
    private Map<String, Item> menu = new HashMap<>();

    private List<Order> processedOrders = new ArrayList<>();
    private List<Observer> observer = new LinkedList<>();
    public LinkedList<Server> servers = new LinkedList<>();
    public LinkedList<Barista> baristas = new LinkedList<>();
    public SharedObject sharedObject;

    //Constructor
    public Manager(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
        initializeMenu();
        viewMenu();
        addServers(2);
        addBaristas(2);
    }

    // Method to create a deep copy of an order
    public static Order copyOrder(Order order) {
        Order copy = new Order();
        copy.setCustomerID(order.getCustomerID());
        copy.setTimestamp(order.getTimestamp());
        copy.setName(order.getName());
        copy.setDiscountPrice(order.getDiscountPrice());
        copy.setInitialPrice(order.getInitialPrice());
        ArrayList newItems = (ArrayList) order.getItems();
        copy.setItems((List) newItems.clone());

        return copy;
    }

    // Method to initialize the menu from the CSV file : menu.csv
    private void initializeMenu() {

        try {
            Item item;
            String message;

            // Read the CSV file
            String menuPath = "menu.csv";
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            // Stop if it is not the last line of the CSV
            while ((line = bufferedReader.readLine()) != null) {

                String[] words = line.split(";");

                try {
                    if (!words[0].matches("((Hot|Cold|Sandwiches|Pastry)_\\d{3})")) {
                        throw new InvalidItemIdException(words[0]);
                    }
                    String category = words[0].split("_")[0];
                    item = new Item(words[1], words[2], category, Float.parseFloat(words[3]), Integer.parseInt(words[4]), Integer.parseInt(words[5]));
                    menu.put(words[0], item);
                    item.setInitialStock(item.getStock());
                } catch (InvalidItemNameException | InvalidItemIdException e) {
                    message = e.getMessage() + "\n Item not added: " + words[1];
                    System.out.println(message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method that can increase stock when closing the window
    public void addStock(int item_nb, int stock_nb) {
        try {
            String line;
            String menuPath = "menu.csv";
            FileInputStream fileInputStream = new FileInputStream(menuPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String[] lines = new String[menu.size()];

            int j = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lines[j] = line;
                j++;
            }
            OutputStream fileOutputStream = new FileOutputStream(menuPath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputStreamWriter);

            for (int i = 0; i < menu.size(); i++) {
                String[] words = lines[i].split(";");
                if (i == item_nb) {
                    int stock = Integer.parseInt(words[4]) + stock_nb;
                    String stock_string = Integer.toString(stock);
                    words[4] = stock_string;
                    lines[i] = words[0] + ";" + words[1] + ";" + words[2] + ";" + words[3] + ";" + words[4];
                    System.out.println(words[0] + ";" + words[1] + ";" + words[2] + ";" + words[3] + ";" + words[4]);
                }

            }

            for (int k = 0; k < menu.size(); k++) {
                System.out.println(lines[k]);
                bufferedwriter.write(lines[k]);
                bufferedwriter.newLine();
            }
            bufferedwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addServers(int nbServers) {
        int serversListSize = servers.size();

        for (int i = 0; i < nbServers - serversListSize; i++) {

            if (servers.size() == 0) {
                // Add server(s) if the maximum number of servers is not reached !
                //Server server = new Server(serversListSize, this, sharedObject);
                Server server = new Server(servers.size() + 1, this, sharedObject);
                servers.add(server);
                // When a new server is added, start this thread
                server.start();


            } else {

                int new_id = 1;

                for (Server server : servers) {
                    if (server.getServerId() == new_id) {
                        new_id++;
                    }

                }

                Server server = new Server(new_id, this, sharedObject);
                servers.add(server);
                // When a new server is added, start this thread
                server.start();


            }


        }
        notifyObservers();
    }

    public void removeServers(int nbServers) {
        int nbActiveServer = servers.size();

        for (Server server : servers) {
            if (nbActiveServer > nbServers) {
                server.stopServer();
                nbActiveServer -= 1;
            }
        }
        notifyObservers();
    }


    public void addBaristas(int nbBaristas) {
        int baristasListSize = baristas.size();
        System.out.println("Baristas before changing : " + baristasListSize);
        for (int i = 0; i < nbBaristas - baristasListSize; i++) {
            // Add barista(s) if the maximum number of baristas is not reached !
            Barista barista = new Barista(baristasListSize + i, this);
            baristas.add(barista);
            // When a new barista is added, start this thread
            barista.start();
        }
        System.out.println("Baristas List Size : " + baristas.size());
    }

    public void removeBaristas(int nbBaristas) {
        int nbActiveBarista = baristas.size();
        if (nbActiveBarista > nbBaristas) {
            for (int i = nbActiveBarista - 1; i >= nbBaristas; i--) {
                baristas.getLast().stopBarista();
            }
        }
        notifyObservers();
    }

    public void removeBarista(Barista barista) {
        barista.stopBarista();
        notifyObservers();
    }

    public void removeServer(Server server) {
        servers.remove(server);
        notifyObservers();
    }


    // method to display the menu in the terminal
    private void viewMenu() {
        for (String id : menu.keySet()) {
            String value = menu.get(id).toString();
            System.out.println("ID: " + id);
            System.out.println(value);
        }
    }

    public void addProcessedOrder(Order order) {
        processedOrders.add(order);
        notifyObservers();
    }

    public Map<String, Item> getMenu() {
        return menu;
    }

    public List<Server> getServers() {
        return servers;
    }

    public List<Barista> getBaristas() {
        return baristas;
    }

    public List<Order> getProcessedOrders() {
        return processedOrders;
    }

    public List<Observer> getObservers() {
        return observer;
    }

    public void registerObserver(Observer obs) {
        observer.add(obs);
    }

    public void removeObserver(Observer obs) {
        observer.remove(obs);
    }

    public void notifyObservers() {
        for (Observer obs : observer) obs.update();
    }

}