/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Core.Product;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Bram ten Brinke
 */  
public class PacketPickerFrame extends JFrame implements ActionListener {

    private PacketPicker pp;
    static ArrayList<Product> selectedPackages = new ArrayList<>();
    private JButton jbOk, jbCancel;
    private TSPWindow tsp;
 
    public PacketPickerFrame(TSPWindow tsp) {
        setTitle("Packet Picker");
        setSize(500, 600);
        setLayout(new FlowLayout());
        setBackground(Color.white);
        
        this.tsp = tsp;
        
         pp = new PacketPicker(this);
         this.add(pp);
         
         
        jbOk = new JButton("confirm");
        jbCancel = new JButton("cancel");

        this.add(jbOk);
        this.add(jbCancel);

        jbOk.addActionListener(this);
        jbCancel.addActionListener(this);

        setVisible(false);
    }

      

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOk) {
            tsp.setOrder(selectedPackages);
            this.setVisible(false);
            repaint();
        }
        if (e.getSource() == jbCancel) {
            this.setVisible(false);
        }
    }

    
}
