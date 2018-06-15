/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Bram ten Brinke
 */
public class PacketPicker extends JPanel implements MouseListener {

    public PacketPicker() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(450, 450));
        this.addMouseListener(this);
        setBackground(Color.white);
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
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
