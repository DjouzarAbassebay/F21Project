package com.company;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        CustomerGUI customerGUI = new CustomerGUI(manager);
        customerGUI.initUI();
    }
}
