/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Core.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Bram ten Brinke
 */
public class PacketPicker extends JPanel implements ActionListener, MouseListener{
    private static int productCounter;
    private static final int rowSize = 84;
    static ArrayList<Product> selectedPackages = new ArrayList<>();
    private JButton jbOk, jbCancel;
    
    
    
    public PacketPicker() {
        setLayout(new FlowLayout());
        setSize(500, 600);
        this.setVisible(true);
        this.addMouseListener(this);
        setBackground(Color.white);
        
        productCounter = 0;
        
        jbOk = new JButton("confirm");
        jbCancel = new JButton("cancel");
        
        this.add(jbOk);
        this.add(jbCancel);
        
        jbOk.addActionListener(this);
        jbCancel.addActionListener(this);
    }
    
    public void PaintComponent(Graphics g) {
        g.setColor(Color.BLACK);

        int lineCounter = 0;
        int positionLine = 15+(lineCounter*70);
        while (lineCounter<6) {
            g.drawLine(15, positionLine, 435, positionLine);
            g.drawLine(positionLine, 15, positionLine, 435);
            lineCounter++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOk) {
            
        }
        if (e.getSource() == jbOk) {
            
        }
    }

@Override
    public void mouseClicked(MouseEvent me) {
        int xHok = me.getY() / rowSize;
        int yHok = me.getX() / rowSize;
        
        Product p = new Product(productCounter, xHok, yHok, Color.CYAN, 30);
        addDeletePoint(p);
        productCounter++;
    }
    
    public void addDeletePoint(Product product) {

        if (selectedPackages.contains(product)) {
            selectedPackages.remove(product);

        } else {
            selectedPackages.add(product);

        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
