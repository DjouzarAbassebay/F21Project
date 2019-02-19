package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static javax.swing.GroupLayout.Alignment.LEADING;


public class CustomerGUI extends JFrame {

    // Variables definition

    // JPanels definition
    private JPanel mainContainerPanel;
    private JPanel welcomePanel;
    private JPanel instructionsPanel;
    private JPanel itemSelectionPanel;
    //private JPanel orderRecapPanel;
    private JPanel buttonsPanel;
    private JPanel settingsPanel;

    private JPanel instructionsPanelSub1;
    private JPanel instructionsPanelSub2;

    // JLabels definition
    private JLabel welcomeLabel;
    private JLabel instructionsLabel;
    private JLabel menuLabel;
    //private JLabel orderRecapLabel;
    private JLabel totalPriceLabel;

    // JButtons definition
    private JButton addButton;
    private JButton deleteButton;
    private JButton removeAllButton;
    private JButton finishButton;
    private JButton reportButton;

    // ArrayLists definition
    private ArrayList<String> itemList;
    private ArrayList<String> itemIDList;

    // Manager definition
    private Manager manager;

    // All other attributs definition
    private JComboBox menuBox;
    private JList<String> orderItems;
    private DefaultListModel<String> orderItemsList;
    private float totalPrice = 0;

    // CustomerGUI Constructor
    public CustomerGUI(Manager manager) {
        this.manager = manager;
        initUI();
    }

    // This function sets the appearance and the functionalities of the top panel
    public void setTopPanel() {

        // Initialize the welcome panel
        welcomePanel = new JPanel();
        // Create a label with an associated text
        welcomeLabel = new JLabel("Welcome to our coffee shop !");
        // Add the label to the welcome panel
        welcomePanel.add(welcomeLabel);
        // Draw a blue line around the panel with the title "Welcome"
        welcomePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Welcome"));

    }

    // This function sets the appearance and the functionalities of the middle panel
    public void setMiddlePanel() {

        // Initialize the JPanels
        instructionsPanel = new JPanel();
        itemSelectionPanel = new JPanel();

        instructionsPanelSub1 = new JPanel();
        instructionsPanelSub2 = new JPanel();

        /////////// SUB 1

        // Initialize the JLabel and the ArrayLists
        menuLabel = new JLabel("Menu : ");
        itemList = new ArrayList<String>();
        itemIDList = new ArrayList<String>();

        // Add the description and the cost of each Item to the itemList ArrayList
        // And add the key of each Item to the itemID ArrayList
        for (Map.Entry<String, Item> entry : manager.menu.entrySet()) {
            itemList.add(entry.getValue().getDescription() + "\t  " + entry.getValue().getCost() + "£");
            itemIDList.add(entry.getKey());
        }

        // Fill the JComboBox with the itemList
        menuBox = new JComboBox<>(itemList.toArray());

        // Create a label with an associated text to give instructions to the user
        instructionsLabel = new JLabel("Please in the menu select and add items to your order !");

        // Set the instructions Panel as a grid of 1 row and 2 columns
        instructionsPanel.setLayout(new GridLayout(1, 2));

        // In the itemSelection panel, add a label and the JComboBox to select items
        itemSelectionPanel.add(menuLabel);
        itemSelectionPanel.add(menuBox);


        // Create a GroupLayout which will be contained in instructionsPanelSub1
        GroupLayout instructionsGroupLayout = new GroupLayout(instructionsPanelSub1);
        // Set the GroupLayout in instructionsPanelSub1
        instructionsPanelSub1.setLayout(instructionsGroupLayout);

        // instructionsGroupLayout Settings
        // Used to define the position of the components of instructionsGroupLayout
        instructionsGroupLayout.setAutoCreateContainerGaps(true);
        instructionsGroupLayout.setAutoCreateGaps(true);
        instructionsGroupLayout.setVerticalGroup(instructionsGroupLayout.createSequentialGroup()
                .addComponent(instructionsLabel)
                .addComponent(itemSelectionPanel)
        );
        instructionsGroupLayout.setHorizontalGroup(instructionsGroupLayout.createSequentialGroup()
                .addGroup(instructionsGroupLayout.createParallelGroup(LEADING)
                        .addComponent(instructionsLabel)
                        .addComponent(itemSelectionPanel)
                )
        );

        ////////// SUB 2

        // Initialize the DefaultListModel and the JList
        orderItemsList = new DefaultListModel<>();
        orderItems = new JList(orderItemsList);

        // Set the orderItems JList to be able to select only one item on click
        orderItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //orderItems.setPreferredSize(new Dimension(200, 200));
        JScrollPane scrollPane = new JScrollPane(orderItems);

        // Get the price of the current order and set it in a label
        totalPrice = manager.currentOrder.getPrice();
        totalPriceLabel = new JLabel("Total : " + totalPrice + " £" );
        totalPriceLabel.setFont(new Font("HelveticaNeue", Font.BOLD, 25));

        // Set a BoxLayout on instructionsPanelSub2
        // And set the positions of all the components in instructionsPanelSub2
        instructionsPanelSub2.setLayout(new BoxLayout(instructionsPanelSub2, BoxLayout.Y_AXIS));
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(100, 200));

        // Add the scrollPane and totalPriceLabel in instructionsPanelSub2
        instructionsPanelSub2.add(scrollPane);
        instructionsPanelSub2.add(totalPriceLabel);

        // Add sub panels to the instructions panel
        instructionsPanel.add(instructionsPanelSub1);
        instructionsPanel.add(instructionsPanelSub2);

        // Draw a blue line around each panel with an associated title
        instructionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Instructions"));
        instructionsPanelSub1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Menu"));
        instructionsPanelSub2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Order Recap"));

    }

    // This function sets the appearance and the functionalities of the bottom panel
    public void setBottomPanel() {

        buttonsPanel = new JPanel();

        // Create buttons and define their title
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        removeAllButton = new JButton("Remove All");
        finishButton = new JButton("Finish");

        // Create listener for the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) menuBox.getSelectedItem();
                int itemIndex = menuBox.getSelectedIndex();
                Item itemSelected;
                itemSelected = manager.menu.get(itemIDList.get(itemIndex));

                // If the selected Item is still available
                // Then add the selected item name in the "Order Recap" List
                if(itemSelected.getStock()>0)
                    orderItemsList.addElement(item);
                else{
                    JOptionPane.showMessageDialog(mainContainerPanel,
                            "Sorry, this product is out of stock.");
                }

                // Print the selected item ID
                System.out.print("\n" + itemIDList.get(itemIndex));

                // Add the selected item in the current order
                manager.currentOrder.addItem(itemSelected);
                // Update order
                manager.currentOrder.updateOrder();

                // Print the selected item
                System.out.println("\n" + manager.menu.get(itemIDList.get(itemIndex)));

                // Print the current order items
                System.out.println("Current Order Items");
                for (int i = 0; i < manager.currentOrder.items.size(); i++) {
                    System.out.print(manager.currentOrder.items.get(i));
                }
                // Set the price to the current order for th item selected
                //manager.currentOrder.setPrice(itemSelected.getCost());

                System.out.println("Price of the current order: £" + manager.currentOrder.getPrice());

                // Display the current price of the order with the User Interface
                totalPrice = manager.currentOrder.getPrice();
                totalPriceLabel.setText("Total : " + totalPrice + " £");

            }
        });

        // Create listener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = orderItems.getSelectedIndex();

                if (index >= 0) {
                    // Remove the item
                    orderItemsList.remove(index);
                    manager.currentOrder.items.get(manager.currentOrder.items.size() - 1).setStock(1);
                    manager.currentOrder.items.remove(index);

                    //update order
                    manager.currentOrder.updateOrder();

                } else {
                    JOptionPane.showMessageDialog(mainContainerPanel,
                            "Before clicking on the delete button, select the item(s) that you want to delete in the Order Recap.");
                }

                // Print the current order items
                System.out.println("Current Order Items");
                for (int i = 0; i < manager.currentOrder.items.size(); i++) {
                    System.out.print(manager.currentOrder.items.get(i));
                }

                System.out.println("Price of the current order: £" + manager.currentOrder.getPrice());

                // Display the current price of the order with the User Interface
                totalPrice = manager.currentOrder.getPrice();
                totalPriceLabel.setText("Total : " + totalPrice + " £");


            }
        });

        // Create listener for the remove button
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all elements in the "Order Recap" List
                orderItemsList.removeAllElements();

                // Remove all elements in the current order
                for(int i=0; i<manager.currentOrder.items.size(); i++)
                {
                    manager.currentOrder.items.get(i).setStock(1);
                }

                // Remove all elements in the current order
                manager.currentOrder.items.clear();

                // Update order
                manager.currentOrder.updateOrder();

                // Print the current order items
                System.out.println("Current Order Items");
                for (int i = 0; i < manager.currentOrder.items.size(); i++) {
                    System.out.print(manager.currentOrder.items.get(i));
                }

                System.out.println("Price of the current order: £" + manager.currentOrder.getPrice());

                // Display the current price of the order with the User Interface
                totalPrice = manager.currentOrder.getPrice();
                totalPriceLabel.setText("Total : " + totalPrice + " £");

            }
        });

        // Create listener for the finish button
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int reply = JOptionPane.showConfirmDialog(instructionsPanel, "If you have finished ordering please click on Yes, otherwise click on No.",
                        "Have you finished ordering ?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    // validate the current order
                    manager.validateCurrentOrder();
                    //Manager.displayOrders();

                    // Remove all elements in the "Order Recap" List
                    orderItemsList.removeAllElements();

                    // Display the current price of the order with the User Interface
                    totalPrice = manager.currentOrder.getPrice();
                    totalPriceLabel.setText("Total : " + totalPrice + " £");

                }
            }
        });

        // Add all buttons to the buttons panel
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(removeAllButton);
        buttonsPanel.add(finishButton);

        // Draw a blue line around the panel with an associated title
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Buttons"));

    }

    public void setSettingsPanel(){

        settingsPanel = new JPanel();

        reportButton = new JButton("Report");
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.generateReport();
            }
        });

        settingsPanel.add(reportButton);
    }

    // This function initializes the User Interface
    public void initUI() {

        // Set a title for the JFrame
        setTitle("Coffee Shop");

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
        setTopPanel();
        setMiddlePanel();
        setBottomPanel();
        setSettingsPanel();

        // Add the welcome panel to the main container panel
        constraints.ipady = 5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        gridBagLayout.setConstraints(welcomePanel, constraints);
        constraints.weightx = 1;
        //constraints.weighty = 1;
        mainContainerPanel.add(welcomePanel);

        // Add the instructions panel to the main container panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        gridBagLayout.setConstraints(instructionsPanel, constraints);
        constraints.weightx = 1;
        //constraints.weighty = 1;
        mainContainerPanel.add(instructionsPanel);

        // Add the buttons panel to the main container panel
        constraints.gridx = 0;
        constraints.gridy = 2;
        gridBagLayout.setConstraints(buttonsPanel, constraints);
        constraints.weightx = 1;
        //constraints.weighty = 1;
        mainContainerPanel.add(buttonsPanel);

        // Add the settings panel to the main container panel
        mainContainerPanel.add(settingsPanel);

        // Add the main container panel to the JFrame
        add(mainContainerPanel);

        // Size optimally all the JFrame's components
        pack();

        // When the JFrame is closed
        // Call the method to generate the report
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.generateReport();
            }
        });

        // On click on the close button, exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the JFrame visible
        setVisible(true);
    }
}
