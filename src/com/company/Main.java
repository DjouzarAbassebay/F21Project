package com.company;

public class Main {

    // Launch the GUI and create an instance manager
    public static void main(String[] args) {

        Manager manager = new Manager();
       // CustomerGUI customerGUI = new CustomerGUI(manager);
       // customerGUI.initUI();

        SettingsGUI settingsGUI = new SettingsGUI(manager);

    }
}