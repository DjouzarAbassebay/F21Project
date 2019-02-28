package com.company;

public class Main {

    // Launch the GUI and create an instance manager
    public static void main(String[] args) {
        int nb_server = 2;
        Manager manager = new Manager();
       // CustomerGUI customerGUI = new CustomerGUI(manager);
       // customerGUI.initUI();


        for(int i=0; i<nb_server;i++)
        {
            Server server1 = new Server(i,manager);
            server1.start();
        }
    }
}