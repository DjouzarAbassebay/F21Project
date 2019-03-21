package com.company.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class SharedObject {

    private LinkedList<Order> orders;
    private List<Order> normalOrders;
    private List<Order> priorityOrders;
    private Manager manager;

    public SharedObject() {
        this.orders = new LinkedList<>();
        this.normalOrders = new ArrayList<>();
        this.priorityOrders = new ArrayList<>();
    }


    public synchronized void addOrderFromNormalOrders(Order order) {
        normalOrders.add(order);
        updateOrders();
    }

    public synchronized void addOrderFromPriorityOrders(Order order) {
        priorityOrders.add(order);
        updateOrders();
    }

    public synchronized void removeOrderFromNormalOrders(Order order) {
        normalOrders.remove(order);
        updateOrders();
    }

    public synchronized void removeOrderFromPriorityOrders(Order order) {
        priorityOrders.remove(order);
        updateOrders();
    }

    public synchronized void updateOrders() {

        LinkedList<Order> temp = new LinkedList<>(priorityOrders);
        temp.addAll(normalOrders);

        orders = temp;

        manager.notifyObservers();

    }


    public synchronized Order getNextOrder() {
        Order order;
        try {
            order = orders.removeFirst();
            manager.notifyObservers();
        } catch (NoSuchElementException e) {
            order = null;
        }
        return order;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getNormalOrders() {
        return normalOrders;
    }

    public List<Order> getPriorityOrders() {
        return priorityOrders;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
