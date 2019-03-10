package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class SharedObject {

    private LinkedList<Order> orders;

    public SharedObject() {
        orders = new LinkedList<>();

    }



    public synchronized void  addOrder(Order order)
    {

        orders.add(order);
    }

    public void viewOrders() {
        System.out.println("Orders List");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // method to display the items in each order in the terminal
    private void displayOrders() {
        for (Order order : orders) {
            System.out.println(order.getCustomerID());
            if (order.getItems().isEmpty())
                System.out.println("Order empty !");
            else {
                for (int j = 0; j < order.getItems().size(); j++) {
                    System.out.println(order.getItems().get(j));
                }
            }
        }
    }

    public synchronized Order getNextOrder(){
        Order order = new Order();
        try{
            order = orders.removeFirst();

        }
        catch(NoSuchElementException e){
            order = null;


        }

        return order;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(LinkedList<Order> orders) {
        this.orders = orders;
    }


}
