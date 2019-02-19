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

    private JPanel mainContainerPanel;
    private JPanel welcomePanel;
    private JPanel instructionsPanel;
    private JPanel itemSelectionPanel;
    //private JPanel orderRecapPanel;
    private JPanel buttonsPanel;

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

    private JComboBox menuBox;
    //private String[] itemList;
    private ArrayList<String> itemList;
    private ArrayList<String> itemIDList;


    private JList<String> orderItems;
    private DefaultListModel<String> orderItemsList;

    private Manager manager;

    public CustomerGUI(Manager manager){
        this.manager = manager;
        initUI();
    }

    public void setTopPanel(){

        welcomePanel = new JPanel();
        // Create a label with an associated text
        welcomeLabel = new JLabel("Welcome to our coffee shop !");
        // Add the label to the panel
        welcomePanel.add(welcomeLabel);
        // Draw a blue line around the panel
        welcomePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Welcome Panel"));

    }

    public void setMiddlePanel(){

        instructionsPanel = new JPanel();
        itemSelectionPanel = new JPanel();

        instructionsPanelSub1 = new JPanel();
        instructionsPanelSub2 = new JPanel();

        // Sub 1
        menuLabel = new JLabel("Menu : ");
        itemList = new ArrayList<String>();
        itemIDList = new ArrayList<String>();

        //for (Item item : Manager.menu.values()){
        /*for (Item item : Manager.menu.values()){
            itemList.add(item.getDescription() + "  " + item.getCost() + "£");
            itemIDList.add(Manager.menu.entrySet());
        }*/

        for (Map.Entry<String, Item> entry : manager.menu.entrySet()){
            itemList.add(entry.getValue().getDescription() + "  " + entry.getValue().getCost() + "£");
            itemIDList.add(entry.getKey());
        }

        for(int i=0; i<itemIDList.size(); i++) {
            System.out.println(itemIDList.get(i));
        }

        menuBox = new JComboBox<>(itemList.toArray());

        // Create a label with an associated text
        instructionsLabel = new JLabel("Please in the menu select and add items to your order !");

        instructionsPanel.setLayout(new GridLayout(1,2));

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

        instructionsPanelSub2.add(scrollPane);

        // Add sub panels to the instructions panel
        instructionsPanel.add(instructionsPanelSub1);
        instructionsPanel.add(instructionsPanelSub2);

        // Draw a blue line around the panel
        instructionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Instructions Panel"));
        instructionsPanelSub1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Menu"));
        instructionsPanelSub2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Order Recap"));

    }

    public void setBottomPanel(){

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

                // Add the selected item name in the "Order Recap" List
                orderItemsList.addElement(item);

                // Print the selected item ID
                System.out.print("\n"+ itemIDList.get(itemIndex));

                // Add the selected item in the current order
                manager.currentOrder.addItem(manager.menu.get(itemIDList.get(itemIndex)));

                // Print the selected item
                System.out.println("\n"+ manager.menu.get(itemIDList.get(itemIndex)));

                // Print the current order items
                System.out.println("Current Order Items");
                for(int i=0; i<manager.currentOrder.items.size() ; i++) {
                    System.out.print(manager.currentOrder.items.get(i));
                }

                System.out.println("Price of the current order: £" + manager.currentOrder.getPrice());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = orderItems.getSelectedIndex();
                if (index >= 0) {
                    orderItemsList.remove(index);
                    manager.currentOrder.items.remove(index);
                }

                // Print the current order items
                System.out.println("Current Order Items");
                for(int i=0; i<manager.currentOrder.items.size() ; i++) {
                    System.out.print(manager.currentOrder.items.get(i));
                }

            }
        });

        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all elements in the "Order Recap" List
                orderItemsList.removeAllElements();

                // Remove all elements of the current oreder
                /*for(int i=0; i<Manager.currentOrder.items.size() ; i++) {
                    Manager.currentOrder.items.remove(i);
                }*/



                // Print the current order items
                /*System.out.println("Current Order Items");
                for(int i=0; i<Manager.currentOrder.items.size() ; i++) {
                    System.out.print(Manager.currentOrder.items.get(i));
                }*/

            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object optionArray[] = { "Validate", "Cancel" };
                /*JOptionPane.showConfirmDialog(instructionsPanel, "If you have finished ordering please click on Validate, otherwise click on Cancel.",
                        "Have you finished ordering ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionArray, optionArray[0]);

*/
                int reply = JOptionPane.showConfirmDialog(instructionsPanel, "If you have finished ordering please click on Validate, otherwise click on Cancel.",
                        "Have you finished ordering ?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    manager.validateCurrentOrder();
                    //Manager.displayOrders();
                    orderItemsList.removeAllElements();
                }
                else {
                    JOptionPane.showMessageDialog(null, "GOODBYE");
                    System.exit(0);
                }

            }
        });

        // Add all buttons to the buttons panel
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(removeAllButton);
        buttonsPanel.add(finishButton);

        // Draw a blue line around the panel
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Buttons Panel"));

    }

    public void initUI(){

        setTitle("Coffee Shop");

        mainContainerPanel = new JPanel();

        GridBagLayout gridBagLayout = new GridBagLayout();
        mainContainerPanel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        setTopPanel();
        setMiddlePanel();
        setBottomPanel();

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

        // Add main container panel to the JFrame
        add(mainContainerPanel);

        pack();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                 manager.calculateIncome();
                 System.out.println("XXXXXXX"+manager.getIncome());

            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
