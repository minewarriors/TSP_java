package Window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author jelle
 */
public class MainWindow extends JFrame implements ActionListener {

    private JLabel JLTitle;
    private JButton JBTSP;
    private JButton JBBPP;
    private JButton JBStorage;
    private TSPWindow tsp;

    public MainWindow(TSPWindow tsp) {
        //Window settings
        setSize(1080, 720);
        setTitle("Retrieval & Storage System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        //declairing other windows and pictures
        this.tsp = tsp;

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
        JBTSP.setPreferredSize(new Dimension(400, 40));
        JBTSP.setFont(new Font("Serif", Font.PLAIN, 30));
        JBTSP.setOpaque(true);

        JBBPP.setForeground(Color.BLACK);
        JBBPP.setPreferredSize(new Dimension(400, 40));
        JBBPP.setFont(new Font("Serif", Font.PLAIN, 30));
        JBBPP.setOpaque(true);

        JBStorage.setForeground(Color.BLACK);
        JBStorage.setPreferredSize(new Dimension(805, 40));
        JBStorage.setFont(new Font("Serif", Font.PLAIN, 30));
        JBStorage.setOpaque(true);

        //Add buttons and labels
        add(JLTitle);
        add(JBBPP);
        add(JBTSP);
        add(JBStorage);

        // add to actionListener
        JBBPP.addActionListener(this);
        JBTSP.addActionListener(this);
        JBStorage.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == JBTSP) {
            tsp.setVisible(true);
        }
    }

    public static void main(String[] args) {
        //TSPWindow TSP = new TSPWindow();
        //MainWindow Menu = new MainWindow(TSP);

    }

}
