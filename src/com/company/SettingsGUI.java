package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class SettingsGUI extends JFrame {

    ///// Variables definition

    // JPanels definition
    private JPanel mainContainerPanel;

    private JPanel runtimePanel;
    private JPanel sliderPanel;

    private JPanel staffPanel;
    private JPanel serversPanel;
    private JPanel baristasPanel;

    private JPanel buttonsPanel;

    // JLabels definition
    private JLabel serversLabel;
    private JLabel baristasLabel;

    // JButtons definition
    private JButton applyButton;
    private JButton cancelButton;

    // JSlider and parameters definition
    private JSlider runtimeSlider;
    private static int RUNTIME_MIN = 1;
    private static int RUNTIME_MAX = 10;
    private static int RUNTIME_INIT = 1;

    //JSpinners definition
    private JSpinner serversSpinner;
    private JSpinner baristasSpinner;

    // Alterable variables
    private int processingSpeed = 1;
    private int serversNumber = 0;
    private int baristasNumber = 0;
    private int serversListSize = 0;

    // Static variables
    private static int SERVERS_MAX = 5;
    private static int BARISTAS_MAX = 5;

    private Manager manager;

    // SettingsGUI Constructor
    SettingsGUI(Manager manager){
        this.manager = manager;
        initUI();
    }


    private void setFirstPanel(){

        // Initialize JPanels
        runtimePanel = new JPanel();
        sliderPanel = new JPanel();

        // Initialize JSlider
        runtimeSlider = new JSlider(RUNTIME_MIN, RUNTIME_MAX, RUNTIME_INIT);

        // Slider settings
        runtimeSlider.setPaintTicks(true);
        runtimeSlider.setPaintLabels(true);
        runtimeSlider.setMajorTickSpacing(1);

        // If Slider value changes...
        // Change the processingTime for each server
        runtimeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                processingSpeed = runtimeSlider.getValue();
                System.out.println("Runtime Speed : " + processingSpeed);
            }
        });

        // Add labels to panels
        sliderPanel.add(runtimeSlider);
        runtimePanel.add(sliderPanel);

        // Draw a blue line around the panel with an associated title
        runtimePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Runtime Speed"));
    }



    private void setSecondPanel(){

        // Initialize JPanels
        staffPanel = new JPanel();

        staffPanel.setLayout(new GridLayout(1, 2, 5, 5));

        // Set the appearance and the functionalities of all panels
        setServersPanel();
        setBaristasPanel();

        // Add labels to panels
        staffPanel.add(serversPanel);
        staffPanel.add(baristasPanel);

        // Draw a blue line around the panel
        staffPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Staff"));
    }

    private void setServersPanel(){

        // Initialize JPanel
        serversPanel = new JPanel();

        // Initialize JLabel
        serversLabel = new JLabel("Server(s)");

        // Initialize JSpinners
        // Initialize the model for the spinner from 0 to 9, in 1 steps and start by the value 5
        SpinnerNumberModel model = new SpinnerNumberModel(manager.servers.size(), 0, SERVERS_MAX, 1);
        serversSpinner = new JSpinner(model);

        serversSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                serversNumber = (int) serversSpinner.getValue();
                System.out.println("Servers : " + serversNumber);
            }
        });

        // Create a GroupLayout which will be contained in serversPanel
        GroupLayout serversGroupLayout = new GroupLayout(serversPanel);
        serversPanel.setLayout(serversGroupLayout);

        // serversGroupLayout Settings
        // Used to define the position of the components of serversGroupLayout
        serversGroupLayout.setAutoCreateContainerGaps(true);
        serversGroupLayout.setAutoCreateGaps(true);
        serversGroupLayout.setVerticalGroup(serversGroupLayout.createSequentialGroup()
                .addGroup(serversGroupLayout.createParallelGroup(LEADING)
                        .addComponent(serversLabel)
                        .addComponent(serversSpinner)
                )
        );
        serversGroupLayout.setHorizontalGroup(serversGroupLayout.createSequentialGroup()
                .addComponent(serversLabel)
                .addComponent(serversSpinner)
        );

        // Draw a blue line around the panel
        serversPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE)));
    }

    private void setBaristasPanel(){

        // Initialize JPanel
        baristasPanel = new JPanel();

        // Initialize JLabel
        baristasLabel = new JLabel("Barista(s)");

        // Initialize JSpinners
        // Initialize the model for the spinner from 0 to 9, in 1 steps and start by the value 5
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, BARISTAS_MAX, 1);
        baristasSpinner = new JSpinner(model);

        baristasSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                baristasNumber = (int) baristasSpinner.getValue();
                System.out.println("Baristas : " + baristasNumber);
            }
        });

        // Create a GroupLayout which will be contained in baristasPanel
        GroupLayout baristasGroupLayout = new GroupLayout(baristasPanel);
        baristasPanel.setLayout(baristasGroupLayout);

        // baristasGroupLayout Settings
        // Used to define the position of the components of baristasGroupLayout
        baristasGroupLayout.setAutoCreateContainerGaps(true);
        baristasGroupLayout.setAutoCreateGaps(true);
        baristasGroupLayout.setVerticalGroup(baristasGroupLayout.createSequentialGroup()
                .addGroup(baristasGroupLayout.createParallelGroup(LEADING)
                        .addComponent(baristasLabel)
                        .addComponent(baristasSpinner)
                )
        );
        baristasGroupLayout.setHorizontalGroup(baristasGroupLayout.createSequentialGroup()
                .addComponent(baristasLabel)
                .addComponent(baristasSpinner)
        );

        // Draw a blue line around the panel
        baristasPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE)));
    }



    private void setThirdPanel(){

        // Initialize JPanels
        buttonsPanel = new JPanel();

        // Initialize JButtons
        applyButton = new JButton("Apply");
        cancelButton = new JButton("Cancel");

        // Create a GroupLayout which will be contained in serversPanel
        GroupLayout buttonsGroupLayout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsGroupLayout);

        // buttonsGroupLayout Settings
        // Used to define the position of the components of buttonsGroupLayout
        buttonsGroupLayout.setAutoCreateContainerGaps(true);
        buttonsGroupLayout.setAutoCreateGaps(true);
        buttonsGroupLayout.setVerticalGroup(buttonsGroupLayout.createSequentialGroup()
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buttonsGroupLayout.createParallelGroup(LEADING)
                        .addComponent(applyButton)
                        .addComponent(cancelButton)
                )
        );
        buttonsGroupLayout.setHorizontalGroup(buttonsGroupLayout.createSequentialGroup()
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(applyButton)
                .addComponent(cancelButton)
        );

        buttonsGroupLayout.linkSize(SwingConstants.HORIZONTAL, applyButton, cancelButton);

        // Draw a blue line around the panel with an associated title
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Buttons"));

        // Set buttons listeners
        setButtonsListeners();

    }


    private void setButtonsListeners(){

        // If the apply button is clicked...
        // Update and apply the selected values
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If we want to add server(s)...
                if(serversNumber > manager.servers.size()){
                    manager.addServers(serversNumber);
                }
                // If we want to remove server(s)...
                else if (serversNumber < serversListSize){

                    for (int i = 1; i < (serversListSize - serversNumber + 1); i++) {

                        // Remove server(s) if the servers list is not empty !
                        if(!manager.servers.isEmpty()) {
                            manager.servers.remove(manager.servers.get(serversListSize-i));
                        } else {
                            JOptionPane.showMessageDialog(mainContainerPanel, "There is no more servers !");
                        }
                    }
                    System.out.println("Servers List Size : " + manager.servers.size());

                }

                // If the servers list is not empty...
                if(!manager.servers.isEmpty()) {

                    // Apply the new processing time for each server
                    for (int i = 0; i < manager.servers.size(); i++) {

                        manager.servers.get(i).processingSpeed = processingSpeed;
                    }
                }
            }
        });

        // If the cancel button is clicked...
        // Put back the previous values
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    runtimeSlider.setValue(manager.servers.get(0).processingSpeed);
                }
                catch (IndexOutOfBoundsException indexException){
                    System.out.println("\nArray index is out of bounds\nUser clicked on the cancel button whereas the servers list is empty.");
                    JOptionPane.showMessageDialog(mainContainerPanel, "Please set parameters and click on the apply button.");
                }

                serversSpinner.setValue(serversListSize);

            }
        });

    }



    // This function initializes the User Interface
    void initUI() {

        // Set a title for the JFrame
        setTitle("Settings");

        // Initialize the mainContainer panel of the JFrame
        mainContainerPanel = new JPanel();

        // Set the mainContainer panel as a GridBagLayout
        GridBagLayout gridBagLayout = new GridBagLayout();
        mainContainerPanel.setLayout(gridBagLayout);

        // Apply horizontal constraints to the GridBagLayout
        // To fix all the panels in the mainContainer panel to the left and right sides
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Set the appearance and the functionalities of all panels
        setFirstPanel();
        setSecondPanel();
        setThirdPanel();

        // Add the runtime panel to the main container panel
        constraints.ipady = 5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        gridBagLayout.setConstraints(runtimePanel, constraints);
        constraints.weightx = 1;
        //constraints.weighty = 1;
        mainContainerPanel.add(runtimePanel);

        // Add the staff panel to the main container panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        gridBagLayout.setConstraints(staffPanel, constraints);
        constraints.weightx = 1;
        //constraints.weighty = 1;
        mainContainerPanel.add(staffPanel);

        // Add the buttons panel to the main container panel
        constraints.gridx = 0;
        constraints.gridy = 2;
        gridBagLayout.setConstraints(buttonsPanel, constraints);
        constraints.weightx = 1;
        //constraints.weighty = 1;
        mainContainerPanel.add(buttonsPanel);

        // Add the main container panel to the JFrame
        add(mainContainerPanel);

        // Size optimally all the JFrame's components
        pack();

        // On click on the close button, exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the JFrame unresizable
        setResizable(false);

        // Set the JFrame visible
        setVisible(true);
    }

}
