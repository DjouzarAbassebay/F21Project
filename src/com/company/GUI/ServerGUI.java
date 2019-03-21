package com.company.GUI;

import com.company.consumer.Server;
import com.company.interfaces.Observer;
import com.company.model.Manager;
import com.company.model.Order;
import com.company.producer.CsvProducer;
import com.company.producer.OnlineProducer;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;


public class ServerGUI extends JFrame implements Observer {

    private JPanel topPanel;
    private JPanel middlePanel;
    JPanel waitingPanel = new JPanel();
    JPanel processingPanel = new JPanel();
    private JScrollPane jscrollWaiting;
    private JScrollPane jscrollProcessing;

    private JLabel lblNbrePeopleWaiting;

    private DefaultListModel<String> waitingDefaultList;
    private DefaultListModel<String> processingDefaultList;
    private JList waitingList;
    private JList processingList;

    private LinkedList<ServerPanel> serverPanels = new LinkedList<>();;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints constraints;
    private static int MAX_COLUMNS = 4;
    private int rows = 0;

    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);

    private Manager manager;
    private CsvProducer csvProducer;

    // Create the application
    public ServerGUI(Manager manager, CsvProducer csvProducer) {
        this.manager = manager;
        this.csvProducer = csvProducer;
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

    private void serverPanels() {
        middlePanel = new JPanel();
        middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        gridBagLayout = new GridBagLayout();
        middlePanel.setLayout(gridBagLayout);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 5, 0, 5);

        updateServers();
    }

    private void buttons() {
        // Online Order button
        JButton btnOnlineOrder = new JButton("Online Order");
        btnOnlineOrder.setForeground(new Color(255, 255, 255));
        btnOnlineOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnOnlineOrder.setBackground(new Color(205, 133, 63));
        btnOnlineOrder.setBounds(240, 571, 154, 29);
        getContentPane().add(btnOnlineOrder);

        // When the button is clicked
        btnOnlineOrder.addActionListener(arg0 -> {
            OnlineProducer onlineProducer = new OnlineProducer(manager.sharedObject, manager.getMenu());
            onlineProducer.initUI();
        });

        // Settings button
        JButton btnSettings = new JButton("Settings");
        btnSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSettings.setForeground(new Color(255, 255, 255));
        btnSettings.setBackground(new Color(205, 133, 63));
        btnSettings.setBounds(430, 571, 135, 29);
        getContentPane().add(btnSettings);

        // When the button is clicked
        btnSettings.addActionListener(arg0 -> {
            SettingsGUI settingsGUI = new SettingsGUI(manager, csvProducer);
            settingsGUI.initUI();
        });

    }


    public void update() {
        updateWaitingQueue();
        updateProcessingQueue();
        updateNumberPeople();
        updateServers();
        if(checkFinished())
            System.exit(0);
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

        int nbPanels = serverPanels.size();
        int nbServers = manager.servers.size();
        if(nbPanels == nbServers) {
            for (int i = 0; i < nbPanels; i++) {
                serverPanels.get(i).updateServerPanel();
            }
        }
        else {
            middlePanel.removeAll();
            serverPanels = new LinkedList<>();
            for(int i = 0; i < manager.servers.size(); i++) {
                serverPanels.add(new ServerPanel(manager.servers.get(i)));
                constraints.ipady = 5;
                constraints.gridx = i % MAX_COLUMNS;
                constraints.gridy = rows;
                middlePanel.add(serverPanels.get(i), constraints);

                if ((i % MAX_COLUMNS) == (MAX_COLUMNS - 1)) {
                    rows++;
                }
            }
            middlePanel.revalidate();
            middlePanel.repaint();
        }
    }


    private class ServerPanel extends JPanel {

        private Server server;
        private JLabel serverNameCustomer;
        private DefaultListModel<String> orderDetailsDefaultList;
        private JLabel serverTotal;

        ServerPanel(Server server) {

            this.server = server;

            // Server Panel
            // Set the mainContainer panel as a GridBagLayout
            GridBagLayout gridBagLayout = new GridBagLayout();
            setLayout(gridBagLayout);
            setBackground(new Color(250, 250, 210));
            setPreferredSize(new Dimension(176, 194));

            // Apply horizontal constraintsPanel to the GridBagLayout
            // To fix all the components in the middle panel vertically and at the center
            GridBagConstraints constraintsPanel = new GridBagConstraints();
            constraintsPanel.fill = GridBagConstraints.VERTICAL;
            constraintsPanel.insets = new Insets(0, 10, 0, 10);

            // Add the label to the server panel
            JLabel serverTitle = new JLabel("Server " + server.getServerId());
            serverTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
            constraintsPanel.ipady = 5;
            constraintsPanel.gridx = 0;
            constraintsPanel.gridy = 0;
            constraintsPanel.weightx = 1;
            add(serverTitle, constraintsPanel);

            // Add the label to the server panel
            serverNameCustomer = new JLabel();
            serverTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
            constraintsPanel.ipadx = 5;
            constraintsPanel.gridx = 0;
            constraintsPanel.gridy = 1;
            constraintsPanel.weightx = 1;
            add(serverNameCustomer, constraintsPanel);

            // Add the JList to the server panel
            orderDetailsDefaultList = new DefaultListModel<>();
            JList serverDetails = new JList(orderDetailsDefaultList);
            DefaultListCellRenderer renderer = (DefaultListCellRenderer) serverDetails.getCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            JScrollPane jscroll = new JScrollPane(serverDetails);


            jscroll.setFont(new Font("Tahoma", Font.PLAIN, 19));
            jscroll.setPreferredSize(new Dimension(146, 89));
            jscroll.setBackground(Color.WHITE);
            constraintsPanel.ipadx = 5;
            constraintsPanel.gridx = 0;
            constraintsPanel.gridy = 2;
            add(jscroll, constraintsPanel);

            // Add the label to the server panel
            serverTotal = new JLabel();
            constraintsPanel.ipadx = 5;
            constraintsPanel.gridx = 0;
            constraintsPanel.gridy = 3;
            constraintsPanel.weightx = 1;
            add(serverTotal, constraintsPanel);

            // Draw a black line around the panel
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));

            updateServerPanel();
        }

        void updateServerPanel() {
            Order order = server.getProcessingOrder();
            // If the server takes an order...
            if (order != null) {
                // Set the associated values in the server panel's components
                serverNameCustomer.setText(order.getName());
                orderDetailsDefaultList.removeAllElements();
                for (int i = 0; i < order.getItems().size(); i++) {
                    orderDetailsDefaultList.addElement(order.getItems().get(i).getName());
                }
                serverTotal.setText(nf.format(order.getInitialPrice()));

            }
            else {
                serverNameCustomer.setText("");
                orderDetailsDefaultList.removeAllElements();
                serverTotal.setText("");
            }
        }

    }


    private boolean checkFinished(){
        if(manager.sharedObject.getOrders().size() != 0)
            return false;
        else if(csvProducer.isAlive())
            return false;
        else{
            for(Server server:manager.getServers())
            {
                if(server.getProcessingOrder()!=null)
                    return false;
            }
        }
        return true;


    }
}


