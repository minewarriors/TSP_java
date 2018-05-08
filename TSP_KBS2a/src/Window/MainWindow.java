/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author jelle
 */
public class MainWindow extends JFrame {

    private JLabel JLTitle;
    private JButton JBTSP;
    private JButton JBBPP;
    private JButton JBStorage;

    public MainWindow() {
        //Window settings
        setSize(1080, 720);
        setTitle("Retrieval & Storage System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        //Declaring Labels
        JLTitle = new JLabel("Storage and Retrieval System");

        //Designing Labels
        JLTitle.setFont(new Font("Serif", Font.PLAIN, 70));
        JLTitle.setBackground(Color.lightGray);
        JLTitle.setForeground(Color.darkGray);
        JLTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JLTitle.setPreferredSize(new Dimension(1000, 80));
        JLTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JLTitle.setVerticalAlignment(SwingConstants.CENTER);

        JLTitle.setOpaque(true);

        //Declaring buttons
        JBTSP = new JButton("TSP Menu");
        JBBPP = new JButton("BPP Menu");
        JBStorage = new JButton("Storage refilling Menu");

        //Designing Buttons
        JBTSP.setForeground(Color.BLACK);

        JBBPP.setForeground(Color.BLACK);

        JBStorage.setForeground(Color.BLACK);

        //Add buttons and labels
        add(JLTitle);
        add(JBBPP);
        add(JBTSP);
        add(JBStorage);

        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow Menu = new MainWindow();
    }

}
