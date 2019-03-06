package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.Font;


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
        // Set the initial parameter of the windows
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel waitingPanel = new JPanel();
        waitingPanel.setBackground(UIManager.getColor("Button.light"));

        JPanel processsingPanel = new JPanel();
        processsingPanel.setBackground(UIManager.getColor("Button.light"));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(SystemColor.controlHighlight);

        JPanel titlePanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(detailsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(waitingPanel, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                                                .addGap(31)
                                                .addComponent(processsingPanel, GroupLayout.PREFERRED_SIZE, 453, Short.MAX_VALUE))
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addGap(263)
                                                .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                                .addGap(251)))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(11)
                                .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(processsingPanel, 0, 0, Short.MAX_VALUE)
                                        .addComponent(waitingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(detailsPanel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                .addGap(6))
        );

        JLabel FcoffeeTaste = new JLabel("F21Coffee   -   Taste the difference");
        titlePanel.add(FcoffeeTaste);
        FcoffeeTaste.setFont(new Font("Dubai Light", Font.BOLD, 26));

        JPanel server1 = new JPanel();

        JLabel lblServer1 = new JLabel("Server 1");
        lblServer1.setFont(new Font("Swis721 Cn BT", Font.BOLD, 23));

        JPanel server2 = new JPanel();

        JLabel lblServer2 = new JLabel("Server 2");
        lblServer2.setFont(new Font("Swis721 Cn BT", Font.BOLD, 23));

        JPanel server3 = new JPanel();

        JLabel lblServer3 = new JLabel("Server 3");
        lblServer3.setFont(new Font("Swis721 Cn BT", Font.BOLD, 23));

        JPanel server4 = new JPanel();

        JLabel lblServer4 = new JLabel("Server 4");
        lblServer4.setFont(new Font("Swis721 Cn BT", Font.BOLD, 23));

        JPanel detailTitle = new JPanel();
        GroupLayout gl_detailsPanel = new GroupLayout(detailsPanel);
        gl_detailsPanel.setHorizontalGroup(
                gl_detailsPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_detailsPanel.createSequentialGroup()
                                .addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_detailsPanel.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(server1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                                .addGap(32)
                                                .addComponent(server2, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                                .addGap(31)
                                                .addComponent(server3, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                .addGap(32)
                                                .addComponent(server4, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                                        .addGroup(gl_detailsPanel.createSequentialGroup()
                                                .addGap(397)
                                                .addComponent(detailTitle, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                                .addGap(324)))
                                .addContainerGap())
        );
        gl_detailsPanel.setVerticalGroup(
                gl_detailsPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_detailsPanel.createSequentialGroup()
                                .addGap(8)
                                .addComponent(detailTitle, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                .addGap(9)
                                .addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_detailsPanel.createSequentialGroup()
                                                .addComponent(server4, GroupLayout.PREFERRED_SIZE, 199, Short.MAX_VALUE)
                                                .addGap(22))
                                        .addGroup(Alignment.TRAILING, gl_detailsPanel.createSequentialGroup()
                                                .addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(server3, 0, 0, Short.MAX_VALUE)
                                                        .addComponent(server2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(server1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(22))))
        );

        JLabel lblDetails = new JLabel("D E T A I L S");
        lblDetails.setFont(new Font("Swis721 Ex BT", Font.PLAIN, 24));
        detailTitle.add(lblDetails);
        GroupLayout gl_server4 = new GroupLayout(server4);
        gl_server4.setHorizontalGroup(
                gl_server4.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_server4.createSequentialGroup()
                                .addGap(61)
                                .addComponent(lblServer4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(64))
        );
        gl_server4.setVerticalGroup(
                gl_server4.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_server4.createSequentialGroup()
                                .addGap(8)
                                .addComponent(lblServer4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(171, Short.MAX_VALUE))
        );
        server4.setLayout(gl_server4);
        GroupLayout gl_server3 = new GroupLayout(server3);
        gl_server3.setHorizontalGroup(
                gl_server3.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_server3.createSequentialGroup()
                                .addGap(59)
                                .addComponent(lblServer3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(54))
        );
        gl_server3.setVerticalGroup(
                gl_server3.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_server3.createSequentialGroup()
                                .addGap(8)
                                .addComponent(lblServer3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(171, Short.MAX_VALUE))
        );
        server3.setLayout(gl_server3);
        GroupLayout gl_server2 = new GroupLayout(server2);
        gl_server2.setHorizontalGroup(
                gl_server2.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_server2.createSequentialGroup()
                                .addGap(72)
                                .addComponent(lblServer2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(61))
        );
        gl_server2.setVerticalGroup(
                gl_server2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_server2.createSequentialGroup()
                                .addGap(8)
                                .addComponent(lblServer2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(171, Short.MAX_VALUE))
        );
        server2.setLayout(gl_server2);

        JLabel lblNameOrderProcessed = new JLabel("");

        JLabel Server1Items = new JLabel("");

        JLabel Server1Total = new JLabel("");
        GroupLayout gl_server1 = new GroupLayout(server1);
        gl_server1.setHorizontalGroup(
                gl_server1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_server1.createSequentialGroup()
                                .addGap(64)
                                .addComponent(lblServer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(69))
                        .addGroup(gl_server1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNameOrderProcessed, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
                        .addGroup(gl_server1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Server1Items, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
                        .addGroup(Alignment.LEADING, gl_server1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Server1Total, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        gl_server1.setVerticalGroup(
                gl_server1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_server1.createSequentialGroup()
                                .addGap(8)
                                .addComponent(lblServer1)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNameOrderProcessed, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(Server1Items, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(Server1Total, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        server1.setLayout(gl_server1);
        detailsPanel.setLayout(gl_detailsPanel);

        JLabel lblProcessing = new JLabel("P R O C E S S I N G . . . ");
        lblProcessing.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        lblProcessing.setBackground(UIManager.getColor("Button.light"));

        JLabel ProcessingName = new JLabel("");

        JLabel ProcessingNumberItems = new JLabel("");
        GroupLayout gl_processsingPanel = new GroupLayout(processsingPanel);
        gl_processsingPanel.setHorizontalGroup(
                gl_processsingPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_processsingPanel.createSequentialGroup()
                                .addGap(92)
                                .addComponent(lblProcessing, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(185))
                        .addGroup(Alignment.LEADING, gl_processsingPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ProcessingName, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                                .addGap(29)
                                .addComponent(ProcessingNumberItems, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(131, Short.MAX_VALUE))
        );
        gl_processsingPanel.setVerticalGroup(
                gl_processsingPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_processsingPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblProcessing, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_processsingPanel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(ProcessingNumberItems, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ProcessingName, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                                .addContainerGap())
        );
        processsingPanel.setLayout(gl_processsingPanel);

        JLabel lblWaiting = new JLabel("W A I T I N G ");
        lblWaiting.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
        lblWaiting.setBackground(UIManager.getColor("Button.light"));
        lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel WaitingName = new JLabel("Emily Walker");
        WaitingName.setBackground(UIManager.getColor("Button.light"));

        JLabel WaintingNumberItems = new JLabel(" 3 Items");
        WaintingNumberItems.setBackground(SystemColor.controlHighlight);
        GroupLayout gl_waitingPanel = new GroupLayout(waitingPanel);
        gl_waitingPanel.setHorizontalGroup(
                gl_waitingPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_waitingPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_waitingPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_waitingPanel.createSequentialGroup()
                                                .addComponent(WaitingName, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                                .addComponent(WaintingNumberItems, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblWaiting, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_waitingPanel.setVerticalGroup(
                gl_waitingPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_waitingPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblWaiting, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_waitingPanel.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_waitingPanel.createSequentialGroup()
                                                .addGap(1)
                                                .addComponent(WaitingName, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGroup(gl_waitingPanel.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(WaintingNumberItems, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        waitingPanel.setLayout(gl_waitingPanel);
        frame.getContentPane().setLayout(groupLayout);

    }
}

