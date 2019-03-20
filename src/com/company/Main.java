package com.company;

import com.company.GUI.ServerGUI;
import com.company.model.Manager;
import com.company.model.Order;
import com.company.model.SharedObject;
import com.company.producer.CsvProducer;

import java.awt.*;
import java.io.IOException;

public class Main {

    // Launch the GUI and create an instance manager
    public static void main(String[] args) throws IOException {


        SharedObject sharedObject = new SharedObject();
        Manager manager = new Manager(sharedObject);
        sharedObject.setManager(manager);
        Order order = new Order();
        CsvProducer csvProducer = new CsvProducer(sharedObject, manager.getMenu());
        csvProducer.start();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    ServerGUI frame = new ServerGUI(manager, csvProducer);
                    frame.setVisible(true);
                    manager.registerObserver(frame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        // CustomerGUI customerGUI = new CustomerGUI(manager);
       // customerGUI.initUI();


     //   SettingsGUI settingsGUI = new SettingsGUI(manager);
    }
}