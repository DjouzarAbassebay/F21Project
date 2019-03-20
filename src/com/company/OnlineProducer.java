package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.*;

import static com.company.Manager.copyOrder;
import static javax.swing.GroupLayout.Alignment.LEADING;


public class OnlineProducer extends JFrame {

    ///// Variables definition

    // JPanels definition
    private JPanel mainContainerPanel;
    private JPanel welcomePanel;
    private JPanel instructionsPanel;
    private JPanel itemSelectionPanel;
    private JPanel buttonsPanel;

    private JPanel orderRecapPanel;
    private JPanel menuPanel;

    private JLabel discountPriceLabel;

    private JButton hotDrinksButton;
    private JButton coldDrinksButton;
    private JButton sandwichesButton;
    private JButton pastryButton;

    // ArrayLists definition
    private ArrayList<String> itemList;
    private ArrayList<String> itemIDList;

    // Manager definition
   // private Manager manager;
    private FinalReport fReport;

    private SharedObject sharedObject;
    Map<String, Item> menu ;

    // All other attributs definition
    private JList<String> orderItems;
    private DefaultListModel<String> orderItemsList;
    private double discountPrice;
    private String categorySelected = "";
    private JTable itemsJtable;

    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);

    private Order currentOrder;


    // OnlineProducer Constructor
    OnlineProducer(SharedObject sharedObject, Map<String, Item> menu){
        //fReport = new FinalReport(sharedObject, manager.menu);
        this.sharedObject = sharedObject;
        this.menu = menu;
        currentOrder = new Order();
    }

    // This function sets the appearance and the functionalities of the top panel
    private void setTopPanel(){

        // Initialize the JPanel
        welcomePanel = new JPanel();
        // Create a label with an associated text
        // JLabels definition
        JLabel welcomeLabel = new JLabel("Welcome to F21Coffee !");
        // Add the label to the panel
        welcomePanel.add(welcomeLabel);
        // Draw a blue line around the panel
        welcomePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Welcome"));

    }

    // This function sets the appearance and the functionalities of the bottom panel
    private void setBottomPanel(){

        // Initialize the JPanel
        buttonsPanel = new JPanel();

        // Create buttons and define their title
        // JButtons definition
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton removeAllButton = new JButton("Remove All");
        JButton finishButton = new JButton("Finish");
        JButton promotionsButton = new JButton("Promotions");

        // Create listener for the add button
        addButton.addActionListener(e -> {
            try {
                // Get the position of the selected row in the JTable
                int row = itemsJtable.getSelectedRow();

                String itemName = itemsJtable.getModel().getValueAt(row, 1).toString();
                String itemPrice = itemsJtable.getModel().getValueAt(row, 3).toString();
                // Between itemName and itemPrice, there is one tabulation and 2 spaces
                String item = itemName + "\t  " + itemPrice;

                Item itemSelected;
                itemSelected = menu.get(itemIDList.get(row));

                // If the selected Item is still available
                // Then add the selected item name in the "Order Recap" List
                if (itemSelected.getStock() > 0)
                    orderItemsList.addElement(item);
                else {
                    JOptionPane.showMessageDialog(mainContainerPanel,
                            "Sorry, this product is out of stock.");
                }

                // Print the selected item ID
                //System.out.print("\n" + itemIDList.get(row));

                // Add the selected item in the current order
                currentOrder.addItem(itemSelected);

                discountPrice = currentOrder.getDiscountPrice();
                discountPriceLabel.setText("Total : " + nf.format(discountPrice));


            } catch (ArrayIndexOutOfBoundsException arrayException){
                System.out.println("\nArray index is out of bounds\nUser clicked on the add button before selecting an item.");
                JOptionPane.showMessageDialog(mainContainerPanel,
                        "Before clicking on the add button, select an item of the menu.");

            } catch (NullPointerException nullPointerException){
                System.out.println("\nNull pointer\nUser clicked on the add button before selecting a category.");
                JOptionPane.showMessageDialog(mainContainerPanel,
                        "Please select a category before clicking on the add button.");
            }

        });

        // Create listener for the delete button
        deleteButton.addActionListener(e -> {

            try {
                // Get the position of the selected item in the order recap
                int index = orderItems.getSelectedIndex();

                // Remove the item
                orderItemsList.remove(index);
                currentOrder.removeItem(index);

            } catch (ArrayIndexOutOfBoundsException arrayException){
                System.out.println("\nArray index is out of bounds\nUser clicked on the delete button before selecting an item in the Order Recap.");
                JOptionPane.showMessageDialog(mainContainerPanel,
                        "Before clicking on the delete button, select the item(s) that you want to delete in the Order Recap.");
            }

        });

        // Create listener for the remove button
        removeAllButton.addActionListener(e -> {
            // Remove all elements in the "Order Recap" List
            orderItemsList.removeAllElements();
            currentOrder.removeAllItem();

            // Display the current price of the order with the User Interface
            discountPrice = currentOrder.getDiscountPrice();
            discountPriceLabel.setText("Total : " + nf.format(discountPrice));

        });

        finishButton.addActionListener(e -> {

            // If the order is not empty...
            if(!currentOrder.getItems().isEmpty()) {
                int reply;
                reply = JOptionPane.showConfirmDialog(instructionsPanel, "If you have finished ordering please click on Yes, otherwise click on No.",
                        "Have you finished ordering ?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    String customerName = JOptionPane.showInputDialog(mainContainerPanel, "Please enter your name.");
                    currentOrder.setName(customerName);

                    validateCurrentOrder();
                    //Manager.displayOrders();

                    // Remove all elements in the "Order Recap" List
                    orderItemsList.removeAllElements();

                    //discountPrice = currentOrder.getDiscountPrice();
                    discountPriceLabel.setText("Total : " + nf.format(0));

                    // Return on the panel to choice the category
                    itemSelectionPanel.removeAll();
                    setMenuCategoryPanel();
                    pack();
                }
            } else {
                JOptionPane.showMessageDialog(mainContainerPanel,
                        "To finish an order, you need to select at least 1 product.");

            }
        });

        promotionsButton.addActionListener(e -> {

            // Create and initialize the JPanels
            JPanel promotionPanel = new JPanel();
            JPanel imagePanel = new JPanel();

            // Create and insert an image in the image label
            ImageIcon promotionIcon = new ImageIcon("src/com/company/images/promotions.jpg");
            JLabel imageLabel = new JLabel(promotionIcon);

            // Add the label to the image panel
            imagePanel.add(imageLabel);

            // Set a GridLayout to mainPanel
            promotionPanel.setLayout(new GridLayout(1, 1, 0, 0));

            // Add the image panel to the promotion panel
            promotionPanel.add(imagePanel);

            // Display in a message dialog the main container panel
            JOptionPane.showMessageDialog(mainContainerPanel, promotionPanel, "F21 Coffee Promotions", JOptionPane.PLAIN_MESSAGE);

        });

        // Add all buttons to the buttons panel
        buttonsPanel.add(promotionsButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(removeAllButton);
        buttonsPanel.add(finishButton);

        // Draw a blue line around the panel with an associated title
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Buttons"));

    }

    // This function sets the appearance and the functionalities of the middle panel
    private void setMiddlePanel(){

        // Initialize the JPanel
        instructionsPanel = new JPanel();

        // Create a GroupLayout which will be contained in instructionsPanelSub1
        GroupLayout instructionsGroupLayout = new GroupLayout(instructionsPanel);
        // Set the GroupLayout in instructionsPanelSub1
        instructionsPanel.setLayout(instructionsGroupLayout);

        // Set the appearance of the 2 panels contained in the instructions Panel
        setMenuPanel();
        setOrderRecapPanel();

        // instructionsGroupLayout Settings
        // Used to define the position of the components of instructionsGroupLayout
        instructionsGroupLayout.setAutoCreateContainerGaps(true);
        instructionsGroupLayout.setAutoCreateGaps(true);
        instructionsGroupLayout.setVerticalGroup(instructionsGroupLayout.createSequentialGroup()
                .addGroup(instructionsGroupLayout.createParallelGroup(LEADING)
                        .addComponent(menuPanel)
                        .addComponent(orderRecapPanel)
                )
        );
        instructionsGroupLayout.setHorizontalGroup(instructionsGroupLayout.createSequentialGroup()
                .addComponent(menuPanel)
                .addComponent(orderRecapPanel)
        );

        // Draw a blue line around the panels with an associated title
        instructionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Instructions"));
        menuPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Menu"));
        orderRecapPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Order Recap"));


    }

    // This function sets the appearance and the functionalities of the menu panel
    // It is the first "sub" panel of the instructions panel (It is on the left side)
    private void setMenuPanel(){

        // Initialize the JPanel
        menuPanel = new JPanel();

        // Create a label with an associated text
        JLabel instructionsLabel = new JLabel("Please in the menu select and add items to your order !");
        instructionsLabel.setFont(new Font("HelveticaNeue", Font.BOLD, 20));

        // Add the border padding around the instructionsLabel
        Border instructionsLabelPadding = BorderFactory.createEmptyBorder(10,10,15,10);
        instructionsLabel.setBorder(instructionsLabelPadding);

        // Initialize the JPanel
        itemSelectionPanel = new JPanel();

        setMenuCategoryPanel();

        // Set a BoxLayout in menuPanel
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Set the position of the instructions label and itemSelection panel in menuPanel
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemSelectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the instructions label and itemSelection panel to menuPanel
        menuPanel.add(instructionsLabel);
        menuPanel.add(itemSelectionPanel);

    }

    // This function sets the appearance and the functionalities of the order recap panel
    // It is the second "sub" panel of the instructions panel (It is on the right side)
    private void setOrderRecapPanel(){

        // Initialize the JPanel
        orderRecapPanel = new JPanel();

        // Initialize the DefaultListModel and the JList
        orderItemsList = new DefaultListModel<>();
        orderItems = new JList(orderItemsList);

        // Set the orderItems JList to be able to select only one item on click
        // And add the orderItems JList to a scrollpane
        orderItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(orderItems);
        scrollPane.setPreferredSize(new Dimension(250, 350));

        // Get the price of the current order and set it in a label
        discountPrice = currentOrder.getDiscountPrice();
        discountPriceLabel = new JLabel("Total : " + nf.format(discountPrice));
        discountPriceLabel.setFont(new Font("HelveticaNeue", Font.BOLD, 25));

        // Set a BoxLayout on orderRecapPanel
        orderRecapPanel.setLayout(new BoxLayout(orderRecapPanel, BoxLayout.Y_AXIS));

        // Set the positions of all the components in orderRecapPanel
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        discountPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the scrollpane and the totalPrice label in orderRecapPanel
        orderRecapPanel.add(scrollPane);
        orderRecapPanel.add(discountPriceLabel);

    }

    // This function sets the appearance and the functionalities of the itemSelection Panel
    // It is the panel which displays firstly category buttons
    private void setMenuCategoryPanel(){

        itemSelectionPanel.setLayout(new GridLayout(2,2));
        createButtonsMenuCategoryPanel();
        setButtonsListenersMenuCategoryPanel();

    }

    // This function creates buttons for the panel linked to the setMenuCategoryPanel function
    private void createButtonsMenuCategoryPanel(){

        // Define a string array for your 4 categories
        String[] itemsCategory = {"Hot drinks", "Cold drinks", "Sandwiches", "Pastry"};

        // Create an imageIcon for each category
        ImageIcon hotDrinksIcon = new ImageIcon("src/com/company/images/hot_drinks.jpg");
        ImageIcon coldDrinksIcon = new ImageIcon("src/com/company/images/cold_drinks.png");
        ImageIcon sandwichesIcon = new ImageIcon("src/com/company/images/sandwiches.jpg");
        ImageIcon pastryIcon = new ImageIcon("src/com/company/images/pastry.jpg");

        // Create a button for the "hot drinks" category
        hotDrinksButton = new JButton(itemsCategory[0], hotDrinksIcon);
        hotDrinksButton.setHorizontalTextPosition(JButton.CENTER);
        hotDrinksButton.setVerticalTextPosition(JButton.BOTTOM);
        hotDrinksButton.setFont(new Font("HelveticaNeue", Font.BOLD, 17));

        // Create a button for the "cold drinks" category
        coldDrinksButton = new JButton(itemsCategory[1], coldDrinksIcon);
        coldDrinksButton.setHorizontalTextPosition(JButton.CENTER);
        coldDrinksButton.setVerticalTextPosition(JButton.BOTTOM);
        coldDrinksButton.setFont(new Font("HelveticaNeue", Font.BOLD, 17));

        // Create a button for the "sandwiches" category
        sandwichesButton = new JButton(itemsCategory[2], sandwichesIcon);
        sandwichesButton.setHorizontalTextPosition(JButton.CENTER);
        sandwichesButton.setVerticalTextPosition(JButton.BOTTOM);
        sandwichesButton.setFont(new Font("HelveticaNeue", Font.BOLD, 17));

        // Create a button for the "pastry" category
        pastryButton = new JButton(itemsCategory[3], pastryIcon);
        pastryButton.setHorizontalTextPosition(JButton.CENTER);
        pastryButton.setVerticalTextPosition(JButton.BOTTOM);
        pastryButton.setFont(new Font("HelveticaNeue", Font.BOLD, 17));

        // Add all these buttons to the itemSelectionPanel
        itemSelectionPanel.add(hotDrinksButton);
        itemSelectionPanel.add(coldDrinksButton);
        itemSelectionPanel.add(sandwichesButton);
        itemSelectionPanel.add(pastryButton);

    }

    // This function creates buttons listeners for the panel linked
    // to the setMenuCategoryPanel function
    private void setButtonsListenersMenuCategoryPanel(){

        // Create listener for the hot drink button
        hotDrinksButton.addActionListener(e -> {
            categorySelected = "Hot";

            // Empty the itemSelectionPanel
            itemSelectionPanel.removeAll();

            fillArrayLists();
            // "Fill" the itemSelectionPanel with items of the selected category
            setMenuItemsPanel();

            // Size optimally all the components
            pack();
        });

        // Create listener for the cold drink button
        coldDrinksButton.addActionListener(e -> {
            categorySelected = "Cold";

            // Empty the itemSelectionPanel
            itemSelectionPanel.removeAll();

            fillArrayLists();
            // "Fill" the itemSelectionPanel with items of the selected category
            setMenuItemsPanel();

            // Size optimally all the components
            pack();
        });

        // Create listener for the sandwiches button
        sandwichesButton.addActionListener(e -> {
            categorySelected = "Sandwiches";

            // Empty the itemSelectionPanel
            itemSelectionPanel.removeAll();

            fillArrayLists();
            // "Fill" the itemSelectionPanel with items of the selected category
            setMenuItemsPanel();

            // Size optimally all the components
            pack();
        });

        // Create listener for the pastry button
        pastryButton.addActionListener(e -> {
            categorySelected = "Pastry";

            // Empty the itemSelectionPanel
            itemSelectionPanel.removeAll();

            fillArrayLists();
            // "Fill" the itemSelectionPanel with items of the selected category
            setMenuItemsPanel();

            // Size optimally all the components
            pack();
        });
    }

    // This function sets the appearance and the functionalities of the itemSelection Panel
    // It is the panel which displays secondly the items of the selected category
    private void setMenuItemsPanel(){

        // Initialize the JPanel
        JPanel previousAndCategoryPanel = new JPanel();

        // Create an button (previous button) in which will be inserted an ImageIcon
        ImageIcon previousIcon = new ImageIcon("src/com/company/images/previous.png");
        JButton previousButton = new JButton(previousIcon);

        // Create listener for this button
        previousButton.addActionListener(e -> {
            itemsJtable.clearSelection();

            // Empty the itemSelectionPanel
            itemSelectionPanel.removeAll();

            // "Fill" the itemSelectionPanel with buttons for each category
            setMenuCategoryPanel();

            // Size optimally all the components
            pack();
        });

        // Create a label to display the selected category after click
        JLabel categoryLabel = new JLabel(categorySelected);
        categoryLabel.setFont(new Font("HelveticaNeue", Font.BOLD, 17));

        // Set a BoxLayout in previousAndCategoryPanel
        previousAndCategoryPanel.setLayout(new BoxLayout(previousAndCategoryPanel, BoxLayout.X_AXIS));
        previousAndCategoryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, previousAndCategoryPanel.getPreferredSize().height));

        // Add the previous button and the category Label to the previousAndCategoryPanel
        // And add also Box.createHorizontalGlue() to put space between them
        previousAndCategoryPanel.add(previousButton);
        previousAndCategoryPanel.add(Box.createHorizontalGlue());
        previousAndCategoryPanel.add(categoryLabel);
        previousAndCategoryPanel.add(Box.createHorizontalGlue());

        // Draw a gray line around the previousAndCategoryPanel
        previousAndCategoryPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY)));

        // Initialize the DefaultTableModel for the JTable
        DefaultTableModel model = new DefaultTableModel();

        // In the model, set the columns name
        String[] columnNames = { "Image", "Name", "Description", "Price" };
        model.setColumnIdentifiers(columnNames);

        ImageIcon itemIcon;
        // Fill model with Objects created from itemList (to get item's details -> description and price)
        // and from itemIDList (to get item's ID and search the associated image)
        for(int i=0; i<itemList.size(); i++){

            String[] details = itemList.get(i).split(";");
            itemIcon = new ImageIcon("src/com/company/images/items/" + itemIDList.get(i) + ".jpg");
            if ((itemIcon).getImageLoadStatus() != MediaTracker.COMPLETE){
                itemIcon = new ImageIcon("src/com/company/images/items/" + "image_not_available.jpg");
            }
            float itemPrice = Float.parseFloat(details[2]);

            Object[] object = new Object[] { itemIcon, details[0],
                    details[1], nf.format(itemPrice) };
            model.addRow(object);
        }

        // Initialize the JTable using the created DefaultTableModel
        itemsJtable = new JTable(model)
        {
            // Use to be able to display images in the JTable
            // Because it returns the class of each column
            // So specific display is done depending on the class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }

        };

        // Create a DefaultTableCellRenderer to center text in JTable cells
        DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();
        defaultRenderer.setHorizontalAlignment( JLabel.CENTER );

        // Create a TextAreaCellRenderer to unwrap text in the column 2 of the JTable
        TextAreaCellRenderer areaRenderer = new TextAreaCellRenderer();
        areaRenderer.setMargin(new Insets(10, 5, 0, 5));

        // Apply the defaultRenderer on the column 1 (item's description) and 3 (item's price)
        // And apply the textAreaRenderer on the column 2 (item's description)
        itemsJtable.getColumnModel().getColumn(1).setCellRenderer(defaultRenderer);
        itemsJtable.getColumnModel().getColumn(2).setCellRenderer(areaRenderer);
        itemsJtable.getColumnModel().getColumn(3).setCellRenderer(defaultRenderer);

        // Set the width of the colums
        itemsJtable.getColumnModel().getColumn(0).setPreferredWidth(70);
        //itemsJtable.getColumnModel().getColumn(1).setPreferredWidth(150);
        itemsJtable.getColumnModel().getColumn(1).setPreferredWidth(70);
        itemsJtable.getColumnModel().getColumn(3).setPreferredWidth(10);

        // Set a row of the JTable non-editable
        itemsJtable.setDefaultEditor(Object.class, null);

        // Set the itemsJtable JTable to be able to select only one item on click
        itemsJtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set the bounds and the row height of the JTable
        itemsJtable.setBounds(30, 40, 200, 300);
        itemsJtable.setRowHeight(100);

        // Create a scrollpane using the JTable
        JScrollPane jScrollPane = new JScrollPane(itemsJtable);
        jScrollPane.setPreferredSize(new Dimension(350, 350));

        // Set a BoxLayout on itemSelectionPanel
        itemSelectionPanel.setLayout(new BoxLayout(itemSelectionPanel, BoxLayout.Y_AXIS));

        // Set the position of the 2 panels in itemSelectionPanel
        previousAndCategoryPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        jScrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Add previousAndCategoryPanel and jScrollPane to the itemSelectionPanel
        itemSelectionPanel.add(previousAndCategoryPanel);
        itemSelectionPanel.add(jScrollPane);
    }

    // This function fills 2 ArrayLists which are used to recognize items in the menu
    private void fillArrayLists(){

        // Initialize the ArrayLists
        itemList = new ArrayList<>();
        itemIDList = new ArrayList<>();

        // Add the description and the cost of each Item to the itemList ArrayList
        // And add the key of each Item to the itemID ArrayList
        for (Map.Entry<String, Item> entry : menu.entrySet()){
            if(entry.getValue().getCategory().equals(categorySelected)){
                //itemList.add(entry.getValue().getDescription() + ";" + entry.getValue().getCost());
                itemList.add(entry.getValue().getName() + ";" + entry.getValue().getDescription() + ";" + entry.getValue().getCost());
                itemIDList.add(entry.getKey());
            }
        }
    }


    // This function adds the current order at the orders (in Shared Object)
    // Then re-initialize the current order to allow to make a new order
    public void validateCurrentOrder() {

        // Default Customer ID if the priority Orders List is empty()
        int customerID = 1 * 100;

        // If the priority Orders List is not empty...
        if (!sharedObject.getPriorityOrders().isEmpty()) {
            customerID = (sharedObject.getPriorityOrders().size() + 1) * 100;
        }

        currentOrder.setCustomerID(customerID);
        currentOrder.setTimestamp(java.time.LocalDateTime.now().toString());
        addOrderToPriorityOrdersSH(copyOrder(currentOrder));

        //displayOrders();
        newCurrentOrder();
    }

    // This function initializes a new current order
    private void newCurrentOrder() {
        currentOrder = new Order();
    }

    // This function adds the input order at the back of the priority orders (in Shared Object)
    // And update the orders (in Shared Object)
    public void addOrderToPriorityOrdersSH(Order order) {

        sharedObject.addOrderFromPriorityOrders(order);
        System.out.println("This order has been correctly added to the shared object: \n"+order.toString()+"\n");

    }




    // This function initializes the User Interface
    void initUI(){

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
        //setSettingsPanel();

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
        //mainContainerPanel.add(settingsPanel);

        // Add the main container panel to the JFrame
        add(mainContainerPanel);

        // Size optimally all the JFrame's components
        pack();

        // When the JFrame is closed
        // Call the method to generate the report
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //fReport.launch();

                /*Scanner sc = new Scanner(System.in);
                int str;
                System.out.println("Do you want to add stock ? Yes = 1 No = 0:");
                str = sc.nextInt();
                while(str != 0 && str != 1){
                    System.out.println("Please enter 1 or 0");
                    str = sc.nextInt();
                }

                while(str == 1) {
                    System.out.println("Which item ?");
                    int item = sc.nextInt();
                    System.out.println("How many ?");
                    int nb_item = sc.nextInt();
                    manager.addStock(item, nb_item);
                    System.out.println("Do you want to add stock ? Yes = 1 No = 0:");
                    str = sc.nextInt();
                    while(str != 0 && str != 1){
                        System.out.println("Please enter 1 or 0");
                        str = sc.nextInt();
                    }
                }*/
            }
        });

        // On click on the close button, exit the application
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the JFrame unresizable
        setResizable(false);

        // Set the JFrame visible
        setVisible(true);
    }

}

