package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class SharedObject {

    private LinkedList<Order> orders;
    private List<Order> normalOrders;
    private List<Order> priorityOrders;

    public SharedObject() {
        this.orders = new LinkedList<>();
        this.normalOrders = new ArrayList<>();
        this.priorityOrders = new ArrayList<>();

    }



    public synchronized void addOrder(Order order)
    {
        orders.add(order);
    }

    public synchronized void addOrderToNormalOrders(Order order)
    {
        normalOrders.add(order);
    }

    public synchronized void addOrderToPriorityOrders(Order order)
    {
        priorityOrders.add(order);
    }

    public synchronized void removeOrderToNormalOrders(Order order)
    {
        normalOrders.remove(order);
    }

    public synchronized void removeOrderToPrioritylOrders(Order order)
    {
        priorityOrders.remove(order);
    }

    public synchronized void updateOrders(){

        LinkedList<Order> temp = new LinkedList<>();

        for(int i=0; i<priorityOrders.size(); i++){
            temp.add(priorityOrders.get(i));
        }
        for(int i=0; i<normalOrders.size(); i++){
            temp.add(normalOrders.get(i));
        }

        orders = temp;

        /*System.out.println("********** ORDERS LIST **********" );
        for(int i=0; i<orders.size(); i++){
            System.out.println(orders.get(i) );
        }
        System.out.println("*******************************" );
*/
    }


    private void viewOrders() {
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
/*            System.out.println("********** ORDERS LEFT LIST **********" );
            for(int i=0; i<orders.size(); i++){
                System.out.println(orders.get(i) );
            }
            System.out.println("*******************************" );
            System.out.println("*******************************" );*/

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

    public List<Order> getNormalOrders() {
        return normalOrders;
    }
    public void setNormalOrders(List<Order> orders) {
        this.normalOrders = orders;
    }

    public List<Order> getPriorityOrders() {
        return priorityOrders;
    }
    public void setPriorityOrders(List<Order> orders) {
        this.priorityOrders = orders;
    }


    public String[] toStringCustomer() {
        String str[] = new String[orders.size()];
        for(int i = 0; i < orders.size(); i++)
            str[i] = orders.get(i).getName() + " : " + orders.get(i).getItems().size() + " Items";
        return  str;
    }
}
