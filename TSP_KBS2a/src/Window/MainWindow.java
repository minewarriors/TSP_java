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

    public MainWindow() {
        //Window settings
        setSize(1080, 720);
        setTitle("Retrieval & Storage System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        //Declaring Labels
        JLTitle = new JLabel("Storage and Retrieval System");
        JLTitle.setFont(new Font("Serif", Font.PLAIN, 70));
        JLTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JLTitle.setPreferredSize(new Dimension(1000, 80));
        JLTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JLTitle.setVerticalAlignment(SwingConstants.CENTER);

        //Declaring buttons
        JBTSP = new JButton("TSP Menu");
        JBBPP = new JButton("BPP Menu");

        //Add buttons and labels
        add(JLTitle);
        add(JBBPP);
        add(JBTSP);

        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow Menu = new MainWindow();
    }

}
