package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        EventQueue.invokeLater(() -> {
            CustomerGUI customerGUI = new CustomerGUI(manager);
        });
    }
}
