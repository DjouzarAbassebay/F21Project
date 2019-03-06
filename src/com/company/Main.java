package com.company;

import java.awt.*;

public class Main {

    // Launch the GUI and create an instance manager
    public static void main(String[] args) {


        SharedObject sharedObject = new SharedObject();
        Manager manager = new Manager(sharedObject);
        CsvProducer csvProducer = new CsvProducer(sharedObject, manager.getMenu());
        csvProducer.start();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerGUI window = new ServerGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // CustomerGUI customerGUI = new CustomerGUI(manager);
       // customerGUI.initUI();

        SettingsGUI settingsGUI = new SettingsGUI(manager);
    }
}