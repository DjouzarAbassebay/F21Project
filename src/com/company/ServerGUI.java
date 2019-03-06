package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;



public class ServerGUI extends JFrame {

    public JFrame frame;



     // Create the application.

    public ServerGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

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

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(SystemColor.desktop, 2));
        topPanel.setBackground(new Color(250, 250, 210));
        topPanel.setBounds(27, 16, 761, 300);
        getContentPane().add(topPanel);
        topPanel.setLayout(null);

        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new LineBorder(SystemColor.desktop));
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setBounds(27, 323, 761, 233);
        getContentPane().add(middlePanel);

        JLabel lblNbrePeopleWaitinig = new JLabel("There are currently 4 people waiting in the queue:");
        lblNbrePeopleWaitinig.setHorizontalAlignment(SwingConstants.CENTER);
        lblNbrePeopleWaitinig.setBounds(55, 45, 644, 20);
        topPanel.add(lblNbrePeopleWaitinig);

    }

    private void waitingPanel()
    {
        //Waiting Panel
        JPanel waitingPanel = new JPanel();
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

        JList waitingList = new JList();
        waitingList.setBounds(31, 46, 291, 147);
        waitingPanel.add(waitingList);
    }

    private void buttons()
    {
        JButton btnOnlineOrder = new JButton("Online Order");
        btnOnlineOrder.setForeground(new Color(255, 255, 255));
        btnOnlineOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnOnlineOrder.setBackground(new Color(205, 133, 63));
        btnOnlineOrder.setBounds(183, 571, 154, 29);
        getContentPane().add(btnOnlineOrder);

        JButton btnSettings = new JButton("Settings");
        btnSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSettings.setForeground(new Color(255, 255, 255));
        btnSettings.setBackground(new Color(205, 133, 63));
        btnSettings.setBounds(363, 571, 135, 29);
        getContentPane().add(btnSettings);

        JButton btnFinish = new JButton("Finish");
        btnFinish.setForeground(new Color(255, 255, 255));
        btnFinish.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnFinish.setBackground(new Color(205, 133, 63));
        btnFinish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnFinish.setBounds(525, 571, 135, 29);
        getContentPane().add(btnFinish);
    }

    private void processingPanel()
    {
        //Processing Panel
        JPanel processingPanel = new JPanel();
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

        JList processingList = new JList();
        processingList.setBounds(28, 47, 302, 146);
        processingPanel.add(processingList);
    }

    private void serverPanels()
    {
        // Server1 Panel
        JPanel server1Panel = new JPanel();
        server1Panel.setBackground(new Color(250, 250, 210));
        server1Panel.setBounds(32, 343, 176, 194);
        getContentPane().add(server1Panel);
        server1Panel.setLayout(null);

        JLabel server1Title = new JLabel("Server1");
        server1Title.setHorizontalAlignment(SwingConstants.CENTER);
        server1Title.setFont(new Font("Tahoma", Font.PLAIN, 19));
        server1Title.setBounds(28, 5, 117, 36);
        server1Panel.add(server1Title);

        JLabel server1NameCustomer = new JLabel("");
        server1NameCustomer.setBounds(15, 41, 146, 20);
        server1Panel.add(server1NameCustomer);

        JLabel server1Total = new JLabel("");
        server1Total.setBounds(49, 158, 69, 20);
        server1Panel.add(server1Total);

        JList server1Details = new JList();
        server1Details.setBounds(15, 67, 146, 89);
        server1Panel.add(server1Details);

        // Server2 Panel
        JPanel server2Panel = new JPanel();
        server2Panel.setBackground(new Color(250, 250, 210));
        server2Panel.setBounds(223, 343, 176, 194);
        getContentPane().add(server2Panel);
        server2Panel.setLayout(null);

        JLabel server2Title = new JLabel("Server2");
        server2Title.setFont(new Font("Tahoma", Font.PLAIN, 19));
        server2Title.setHorizontalAlignment(SwingConstants.CENTER);
        server2Title.setBounds(30, 5, 113, 33);
        server2Panel.add(server2Title);

        JLabel server2NameCustomer = new JLabel("");
        server2NameCustomer.setBounds(15, 41, 146, 20);
        server2Panel.add(server2NameCustomer);

        JLabel server2Total = new JLabel("");
        server2Total.setBounds(50, 158, 69, 20);
        server2Panel.add(server2Total);

        JList server2Details = new JList();
        server2Details.setBounds(15, 70, 146, 85);
        server2Panel.add(server2Details);

        // Server3 Panel
        JPanel server3Panel = new JPanel();
        server3Panel.setBackground(new Color(250, 250, 210));
        server3Panel.setBounds(414, 343, 176, 194);
        getContentPane().add(server3Panel);
        server3Panel.setLayout(null);

        JLabel server3Title = new JLabel("Server3");
        server3Title.setFont(new Font("Tahoma", Font.PLAIN, 19));
        server3Title.setHorizontalAlignment(SwingConstants.CENTER);
        server3Title.setBounds(29, 5, 117, 30);
        server3Panel.add(server3Title);

        JLabel server3NameCustomer = new JLabel("");
        server3NameCustomer.setBounds(15, 41, 146, 20);
        server3Panel.add(server3NameCustomer);

        JLabel server3Total = new JLabel("");
        server3Total.setBounds(54, 170, 69, 20);
        server3Panel.add(server3Total);

        JList server3Details = new JList();
        server3Details.setBounds(15, 69, 146, 85);
        server3Panel.add(server3Details);

        // Server4 Panel
        JPanel server4Panel = new JPanel();
        server4Panel.setBackground(new Color(250, 250, 210));
        server4Panel.setBounds(605, 343, 176, 194);
        getContentPane().add(server4Panel);
        server4Panel.setLayout(null);

        JLabel server4Title = new JLabel("Server4");
        server4Title.setFont(new Font("Tahoma", Font.PLAIN, 19));
        server4Title.setHorizontalAlignment(SwingConstants.CENTER);
        server4Title.setBounds(41, 5, 98, 34);
        server4Panel.add(server4Title);

        JLabel server4NameCustomer = new JLabel("");
        server4NameCustomer.setBounds(15, 42, 146, 20);
        server4Panel.add(server4NameCustomer);

        JLabel server4Total = new JLabel("");
        server4Total.setBounds(55, 158, 69, 20);
        server4Panel.add(server4Total);

        JList server4Details = new JList();
        server4Details.setBounds(15, 70, 146, 85);
        server4Panel.add(server4Details);

    }

}

