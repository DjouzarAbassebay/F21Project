package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.invoke.SerializedLambda;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ServerGUI extends JFrame implements Observer {

    private JPanel topPanel;
    private JPanel middlePanel;

    private static int MAX_COLUMNS = 4;
    private int rows = 0;
    private static int MAX_SERVERS = 50;
    private int nb_servers = 3;
    private int count = 0;

    JPanel waitingPanel = new JPanel();
    JPanel processingPanel = new JPanel();

    private JScrollPane jscrollWaiting;
    private JScrollPane jscrollProcessing;

    private JLabel lblNbrePeopleWaiting;

    private GridBagConstraints constraints;
    private GridBagLayout gridBagLayout;

    private DefaultListModel<String> waitingDefaultList;
    private DefaultListModel<String> processingDefaultList;

    private JList waitingList;
    private JList processingList;

    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);

    private Manager manager;

    // Create the application
    public ServerGUI(Manager manager) {
        this.manager = manager;
        constraints = new GridBagConstraints();
        initialize();
    }


    private void initialize() {

        setResizable(false);

        JLabel lblF21Coffee = new JLabel("F21Coffee - Feel the difference");
        lblF21Coffee.setBackground(SystemColor.menu);
        lblF21Coffee.setHorizontalAlignment(SwingConstants.CENTER);
        lblF21Coffee.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblF21Coffee.setBounds(192, 16, 419, 40);
        getContentPane().add(lblF21Coffee);

        waitingPanel();
        processingPanel();
        serverPanels();
        buttons();

        getContentPane().setBackground(new Color(255, 255, 240));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 50, 835, 650);
        getContentPane().setLayout(null);
        getContentPane().setLayout(null);

        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(SystemColor.desktop, 2));
        topPanel.setBackground(new Color(250, 250, 210));
        topPanel.setBounds(27, 16, 761, 300);
        getContentPane().add(topPanel);
        topPanel.setLayout(null);

        middlePanel.setBorder(new LineBorder(SystemColor.desktop));
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setBounds(27, 323, 761, 233);
        getContentPane().add(middlePanel);

        System.out.println(manager.sharedObject.getOrders().size());
        lblNbrePeopleWaiting = new JLabel("There are currently " + manager.sharedObject.getOrders().size() + " people waiting in the queue:");
        lblNbrePeopleWaiting.setHorizontalAlignment(SwingConstants.CENTER);
        lblNbrePeopleWaiting.setBounds(55, 45, 644, 20);
        topPanel.add(lblNbrePeopleWaiting);

    }


    private void waitingPanel() {
        //Waiting Panel

        waitingPanel.setBorder(new LineBorder(SystemColor.textInactiveText));
        waitingPanel.setBackground(UIManager.getColor("Button.light"));
        waitingPanel.setBounds(32, 98, 356, 209);
        getContentPane().add(waitingPanel);
        waitingPanel.setLayout(null);

        JLabel lblWaiting = new JLabel("W A I T I N G");
        lblWaiting.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);
        lblWaiting.setBounds(72, 16, 202, 26);
        waitingPanel.add(lblWaiting);

        waitingDefaultList = new DefaultListModel<>();
        waitingList = new JList(waitingDefaultList);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) waitingList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jscrollWaiting = new JScrollPane(waitingList);

        jscrollWaiting.setBounds(31, 46, 291, 147);
        waitingList.setBounds(0, 0, 291, 147);

        waitingPanel.add(jscrollWaiting);

        updateWaitingQueue();

    }


    private void processingPanel() {
        //Processing Panel
        processingPanel = new JPanel();
        processingPanel.setBorder(new LineBorder(SystemColor.textInactiveText));
        processingPanel.setBackground(UIManager.getColor("Button.light"));
        processingPanel.setBounds(425, 98, 356, 209);
        getContentPane().add(processingPanel);
        processingPanel.setLayout(null);

        JLabel lblProcessing = new JLabel("P R O C E S S I N G . . . ");
        lblProcessing.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblProcessing.setHorizontalAlignment(SwingConstants.CENTER);
        lblProcessing.setBounds(67, 16, 232, 28);
        processingPanel.add(lblProcessing);

        processingDefaultList = new DefaultListModel<>();
        processingList = new JList(processingDefaultList);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) processingList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jscrollProcessing = new JScrollPane(processingList);

        jscrollProcessing.setBounds(31, 46, 291, 147);
        processingList.setBounds(0, 0, 291, 147);
        processingPanel.add(jscrollProcessing);

        updateProcessingQueue();
    }


    private class ServerPanel extends JPanel {

        private Server server;

        public ServerPanel(Server server) {

            this.server = server;

            // Server Panel
            /*setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(250, 250, 210));
            setPreferredSize(new Dimension(176, 194));
            //setBounds(32, 343, 176, 194);

            JLabel serverTitle = new JLabel("Server");
            serverTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
            serverTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
            serverTitle.setPreferredSize(new Dimension(117, 36));
            //serverTitle.setBounds(28, 5, 117, 36);
            add(serverTitle);

            Order order = manager.getServers().get(0).processingOrder;
            JLabel serverNameCustomer = new JLabel(order.getName());
            serverNameCustomer.setAlignmentY(Component.CENTER_ALIGNMENT);
            //serverNameCustomer.setPreferredSize(new Dimension(146, 20));
            serverNameCustomer.setBounds(15, 41, 146, 20);
            add(serverNameCustomer);

            DefaultListModel<String> orderDetailsDefaultList = new DefaultListModel<>();
            for(int i=0; i<order.getItems().size(); i++){
                orderDetailsDefaultList.addElement(order.getItems().get(i).getName());
            }
            JList serverDetails = new JList(orderDetailsDefaultList);
            DefaultListCellRenderer renderer =  (DefaultListCellRenderer)serverDetails.getCellRenderer();
            renderer.setAlignmentY(Component.CENTER_ALIGNMENT);
            JScrollPane jScrollPane = new JScrollPane(serverDetails);
            //jScrollPane.setPreferredSize(new Dimension(146, 89));
            jScrollPane.setBounds(15, 67, 146, 89);
            add(jScrollPane);

            JLabel serverTotal = new JLabel(order.getInitialPrice() + " £");
            serverTotal.setAlignmentY(Component.CENTER_ALIGNMENT);
            //serverTotal.setBounds(49, 158, 69, 20);
            serverTotal.setPreferredSize(new Dimension(69, 20));
            add(serverTotal);

            setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)) );
            */


            // Server Panel
            // Set the mainContainer panel as a GridBagLayout
            GridBagLayout gridBagLayout = new GridBagLayout();
            setLayout(gridBagLayout);
            setBackground(new Color(250, 250, 210));
            setPreferredSize(new Dimension(176, 194));

            // Apply horizontal constraints to the GridBagLayout
            // To fix all the panels in the mainContainer panel to the left and right sides
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(0, 0, 0, 5);

            // Add the label to the server panel
            JLabel serverTitle = new JLabel("Server " + server.getId());
            serverTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
            serverTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
            constraints.ipady = 5;
            constraints.gridx = 0;
            constraints.gridy = 0;
            gridBagLayout.setConstraints(serverTitle, constraints);
            constraints.weightx = 1;
            add(serverTitle);

            Order order = server.getProcessingOrder();

            JLabel serverNameCustomer = new JLabel();
            serverTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
            constraints.ipadx = 5;
            constraints.gridx = 0;
            constraints.gridy = 1;
            gridBagLayout.setConstraints(serverNameCustomer, constraints);
            constraints.weightx = 1;

            DefaultListModel<String> orderDetailsDefaultList = new DefaultListModel<>();
            //List<Item> items = order1.getItems();

            JList serverDetails = new JList(orderDetailsDefaultList);
            DefaultListCellRenderer renderer = (DefaultListCellRenderer) serverDetails.getCellRenderer();
            renderer.setAlignmentY(Component.CENTER_ALIGNMENT);
            JScrollPane jscroll = new JScrollPane(serverDetails);


            jscroll.setFont(new Font("Tahoma", Font.PLAIN, 19));
            jscroll.setPreferredSize(new Dimension(146, 89));
            jscroll.setBackground(Color.WHITE);
            constraints.ipadx = 5;
            constraints.gridx = 0;
            constraints.gridy = 2;
            gridBagLayout.setConstraints(jscroll, constraints);
            //constraints.weightx = 1;


            JLabel serverTotal = new JLabel();
            //serverTotal.setAlignmentY(Component.CENTER_ALIGNMENT);
            constraints.ipadx = 5;
            constraints.gridx = 0;
            constraints.gridy = 3;
            gridBagLayout.setConstraints(serverTotal, constraints);
            constraints.weightx = 1;


            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));

            if (order != null)
            {

                serverNameCustomer.setText(order.getName());
                add(serverNameCustomer);

                for (int i = 0; i < order.getItems().size(); i++) {
                    orderDetailsDefaultList.addElement(order.getItems().get(i).getName());
                }
                add(jscroll);

                serverTotal.setText(order.getInitialPrice() + " £");
                add(serverTotal);
            }

        }

    }

    private void serverPanels() {

        middlePanel = new JPanel();
        middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        gridBagLayout = new GridBagLayout();
        middlePanel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        /*for (int i = 0; i < nb_servers; i++) {

            // Create a serverPanel
            ServerPanel panel = new ServerPanel(manager.servers.get(i));

            // Apply constraints on the serverPanel
            // And Add it to the panels panel
            constraints.ipady = 5;
            constraints.gridx = i % MAX_COLUMNS;
            constraints.gridy = rows;
            gridBagLayout.setConstraints(panel, constraints);
            middlePanel.add(panel, constraints);
            middlePanel.add(Box.createHorizontalStrut(5));

            System.out.println("Server");
            System.out.println("Columns : " + i % MAX_COLUMNS);
            System.out.println("Rows : " + rows);

            if ((i % MAX_COLUMNS) == (MAX_COLUMNS - 1)) {
                rows++;
            }

            //panelsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Servers"));
        }*/

        updateServers();
    }


    public void update() {
        updateWaitingQueue();
        updateProcessingQueue();
        updateNumberPeople();
        updateServers();
    }

    private void buttons() {
        // Online Order button
        JButton btnOnlineOrder = new JButton("Online Order");
        btnOnlineOrder.setForeground(new Color(255, 255, 255));
        btnOnlineOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnOnlineOrder.setBackground(new Color(205, 133, 63));
        btnOnlineOrder.setBounds(183, 571, 154, 29);
        getContentPane().add(btnOnlineOrder);

        // When the button is clicked
        btnOnlineOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                OnlineProducer onlineProducer = new OnlineProducer(manager.sharedObject, manager.getMenu());
                onlineProducer.initUI();
            }
        });

        // Settings button
        JButton btnSettings = new JButton("Settings");
        btnSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSettings.setForeground(new Color(255, 255, 255));
        btnSettings.setBackground(new Color(205, 133, 63));
        btnSettings.setBounds(363, 571, 135, 29);
        getContentPane().add(btnSettings);

        // When the button is clicked
        btnSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SettingsGUI settingsGUI = new SettingsGUI(manager);
                settingsGUI.initUI();
            }
        });

        // Finish button
        JButton btnFinish = new JButton("MAJ");
        btnFinish.setForeground(new Color(255, 255, 255));
        btnFinish.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnFinish.setBackground(new Color(205, 133, 63));
        btnFinish.setBounds(525, 571, 135, 29);
        getContentPane().add(btnFinish);

        // When the button is clicked
        btnFinish.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //waitingPanel();
                //waitingDefaultList = new DefaultListModel<>();
                /*for(int i=0; i<manager.sharedObject.getOrders().size(); i++){
                    System.out.println(manager.sharedObject.getOrders().get(i).getName());
                    waitingDefaultList.addElement(manager.sharedObject.getOrders().get(i).getName());
                }*/
                //serverPanels();
                update();
                System.out.println("ooooooooooooooooooooooo  " + waitingDefaultList.size());
            }
        });
    }

    private void updateWaitingQueue() {
        waitingDefaultList.removeAllElements();
        for (int i = 0; i < manager.sharedObject.getOrders().size(); i++) {
            waitingDefaultList.addElement(manager.sharedObject.getOrders().get(i).getName());
        }
    }

    private void updateProcessingQueue() {
        processingDefaultList.removeAllElements();
        for (int i = 0; i < manager.servers.size(); i++) {
            Order processingOrder = manager.servers.get(i).getProcessingOrder();
            if (processingOrder != null) {
                processingDefaultList.addElement(processingOrder.getName());
            }
        }
    }

    private void updateNumberPeople() {
        lblNbrePeopleWaiting.setText("There are currently " + manager.sharedObject.getOrders().size() + " people waiting in the queue:");
    }


    private void updateServers() {

        for (int i = 0; i < manager.servers.size(); i++) {

            // Create a serverPanel
            ServerPanel panel = new ServerPanel(manager.servers.get(i));

            // Apply constraints on the serverPanel
            // And Add it to the panels panel
            constraints.ipady = 5;
            constraints.gridx = i % MAX_COLUMNS;
            constraints.gridy = rows;
            gridBagLayout.setConstraints(panel, constraints);
            middlePanel.add(panel, constraints);
            middlePanel.add(Box.createHorizontalStrut(5));

            System.out.println("Server");
            System.out.println("Columns : " + i % MAX_COLUMNS);
            System.out.println("Rows : " + rows);

            if ((i % MAX_COLUMNS) == (MAX_COLUMNS - 1)) {
                rows++;
            }

        }

    }
}


