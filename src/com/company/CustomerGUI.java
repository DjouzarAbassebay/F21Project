package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static javax.swing.GroupLayout.Alignment.LEADING;


public class CustomerGUI extends JFrame {

    private JPanel mainContainerPanel;
    private JPanel welcomePanel;
    private JPanel instructionsPanel;
    private JPanel itemSelectionPanel;
    //private JPanel orderRecapPanel;
    private JPanel buttonsPanel;
    private JPanel settingsPanel;

    private JPanel instructionsPanelSub1;
    private JPanel instructionsPanelSub2;

    private JLabel welcomeLabel;
    private JLabel instructionsLabel;
    private JLabel menuLabel;
    //private JLabel orderRecapLabel;

    private JButton addButton;
    private JButton deleteButton;
    private JButton removeAllButton;
    private JButton finishButton;
    private JButton reportButton;

    private JComboBox menuBox;
    //private String[] itemList;
    private ArrayList<String> itemList;
    private ArrayList<String> itemIDList;


    private JList<String> orderItems;
    private DefaultListModel<String> orderItemsList;
    private float totalPrice = 0;
    private JLabel totalPriceLabel;

    private Manager manager;

    public CustomerGUI(Manager manager) {
        this.manager = manager;
        initUI();
    }

    public void setTopPanel() {

        welcomePanel = new JPanel();
        // Create a label with an associated text
        welcomeLabel = new JLabel("Welcome to our coffee shop !");
        // Add the label to the panel
        welcomePanel.add(welcomeLabel);
        // Draw a blue line around the panel
        welcomePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Welcome"));

    }

    public void setMiddlePanel() {

        instructionsPanel = new JPanel();
        itemSelectionPanel = new JPanel();

        instructionsPanelSub1 = new JPanel();
        instructionsPanelSub2 = new JPanel();

        // Sub 1
        menuLabel = new JLabel("Menu : ");
        itemList = new ArrayList<String>();
        itemIDList = new ArrayList<String>();

        for (Map.Entry<String, Item> entry : manager.menu.entrySet()) {
            itemList.add(entry.getValue().getDescription() + "\t  " + entry.getValue().getCost() + "£");
            itemIDList.add(entry.getKey());
        }

        /*for (int i = 0; i < itemIDList.size(); i++) {
            System.out.println(itemIDList.get(i));
        }*/

        menuBox = new JComboBox<>(itemList.toArray());

        // Create a label with an associated text
        instructionsLabel = new JLabel("Please in the menu select and add items to your order !");

        instructionsPanel.setLayout(new GridLayout(1, 2));

        itemSelectionPanel.add(menuLabel);
        itemSelectionPanel.add(menuBox);


        // Create a GroupLayout which will be contained in instructionsPanelSub1
        GroupLayout instructionsGroupLayout = new GroupLayout(instructionsPanelSub1);
        // Set the GroupLayout in instructionsPanelSub1
        instructionsPanelSub1.setLayout(instructionsGroupLayout);

        // instructionsGroupLayout Settings
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

        // Sub 2
        orderItemsList = new DefaultListModel<>();
        orderItems = new JList(orderItemsList);

        orderItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //orderItems.setPreferredSize(new Dimension(200, 200));
        JScrollPane scrollPane = new JScrollPane(orderItems);

        totalPrice = manager.currentOrder.getPrice();
        totalPriceLabel = new JLabel("Total : " + totalPrice + " £" );
        totalPriceLabel.setFont(new Font("HelveticaNeue", Font.BOLD, 25));

        instructionsPanelSub2.setLayout(new BoxLayout(instructionsPanelSub2, BoxLayout.Y_AXIS));
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(100, 200));

        instructionsPanelSub2.add(scrollPane);
        instructionsPanelSub2.add(totalPriceLabel);

        // Add sub panels to the instructions panel
        instructionsPanel.add(instructionsPanelSub1);
        instructionsPanel.add(instructionsPanelSub2);

        // Draw a blue line around the panel
        instructionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Instructions"));
        instructionsPanelSub1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Menu"));
        instructionsPanelSub2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Order Recap"));

    }

    public void setBottomPanel() {

        buttonsPanel = new JPanel();

        // Create buttons and define their title
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        removeAllButton = new JButton("Remove All");
        finishButton = new JButton("Finish");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) menuBox.getSelectedItem();
                int itemIndex = menuBox.getSelectedIndex();
                Item itemSelected;

                // Add the selected item name in the "Order Recap" List
                orderItemsList.addElement(item);

                // Print the selected item ID
                System.out.print("\n" + itemIDList.get(itemIndex));

                // Add the selected item in the current order
                itemSelected = manager.menu.get(itemIDList.get(itemIndex));
                manager.currentOrder.addItem(itemSelected);
                //update order
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

                totalPrice = manager.currentOrder.getPrice();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        totalPriceLabel.setText("Total : " + totalPrice + " £");

                    }
                });

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = orderItems.getSelectedIndex();
                if (index >= 0) {
                    // remove the item
                    orderItemsList.remove(index);
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

                totalPrice = manager.currentOrder.getPrice();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        totalPriceLabel.setText("Total : " + totalPrice + " £");

                    }
                });

            }
        });

        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all elements in the "Order Recap" List
                orderItemsList.removeAllElements();

                // Remove all elements in the current order
                manager.currentOrder.items.clear();

                //update order
                manager.currentOrder.updateOrder();


                // Print the current order items
                System.out.println("Current Order Items");
                for (int i = 0; i < manager.currentOrder.items.size(); i++) {
                    System.out.print(manager.currentOrder.items.get(i));
                }

                System.out.println("Price of the current order: £" + manager.currentOrder.getPrice());

                totalPrice = manager.currentOrder.getPrice();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        totalPriceLabel.setText("Total : " + totalPrice + " £");

                    }
                });
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int reply = JOptionPane.showConfirmDialog(instructionsPanel, "If you have finished ordering please click on Yes, otherwise click on No.",
                        "Have you finished ordering ?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    manager.validateCurrentOrder();
                    //Manager.displayOrders();
                    orderItemsList.removeAllElements();
                }
            }
        });

        // Add all buttons to the buttons panel
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(removeAllButton);
        buttonsPanel.add(finishButton);

        // Draw a blue line around the panel
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

    public void initUI() {

        setTitle("Coffee Shop");

        mainContainerPanel = new JPanel();

        GridBagLayout gridBagLayout = new GridBagLayout();
        mainContainerPanel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

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

        // Add main container panel to the JFrame
        add(mainContainerPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
