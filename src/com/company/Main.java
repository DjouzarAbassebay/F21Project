package com.company;

public class Main {

    // Launch the GUI and create an instance manager
    public static void main(String[] args) {


        SharedObject sharedObject = new SharedObject();
        Manager manager = new Manager(sharedObject);

        CsvProducer csvProducer = new CsvProducer(sharedObject, manager.getMenu());
        csvProducer.start();
        OnlineProducer onlineProducer = new OnlineProducer(sharedObject, manager.getMenu());
        onlineProducer.initUI();

        SettingsGUI settingsGUI = new SettingsGUI(manager);
    }
}