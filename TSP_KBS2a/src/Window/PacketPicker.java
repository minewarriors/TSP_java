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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Bram ten Brinke
 */
public class PacketPicker extends JPanel implements MouseListener {

    private static int productCounter = 1;
    private static final int panelSize = 450, margin = 15, totalSize = 420 ,boxSize = totalSize/5 ;
    private ArrayList<Product> selectedPackages = PacketPickerFrame.selectedPackages;

    public PacketPicker(PacketPickerFrame ppd) {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(panelSize, panelSize));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //lijnen tekenen
        g.setColor(Color.BLACK);
        int lineCounter = 0;
        while (lineCounter < 6) {
            int positionLine = margin + (lineCounter * boxSize);
            g.drawLine(margin, positionLine, totalSize+margin, positionLine);
            g.drawLine(positionLine, margin, positionLine, totalSize+margin);
            lineCounter++;
        }
        //pakketjes in vakjes zetten
        int packageSize = boxSize/2;
        
        for (Product p: selectedPackages) {
            g.setColor(p.getColor());
            int yPos = margin+(packageSize/2)+(p.getY()*boxSize);
            int xPos = margin+(packageSize/2)+(p.getX()*boxSize);
            g.fillRect(xPos, yPos, packageSize, packageSize);
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getY() > margin && me.getY() < totalSize+margin && me.getX() > margin && me.getX() < totalSize+margin) {
        int yHok = ((me.getY()-margin) / boxSize);
        int xHok = ((me.getX()-margin) / boxSize);
            System.out.println("X = "+xHok+" Y = "+yHok);
        Product p = new Product(productCounter, xHok, yHok, Color.CYAN, 30);
        addDeletePoint(p);
        productCounter++;
        repaint();
        }
    }

    public void addDeletePoint(Product product) {
        if (!selectedPackages.isEmpty()) {
            for (Product p : selectedPackages) {
                if (product.equals(p)) {
                    selectedPackages.remove(p);
                    return;
                }
            }
        }
        selectedPackages.add(product);
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
