package Window;

import Core.Order;
import Core.Product;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    private Order order;

    public DrawPanel(Order order) {
        this.order = order;

        this.setPreferredSize(new Dimension(1080, 600));
        setBackground(Color.WHITE);
    }

    public void drawBigProduct(Graphics g, int x, int y) {
        if (x > 0 && y > 0) {
            if (x != 1) {
                x = -150 + (x * 200);
            } else {
                x = 50;
            }
            if (y != 1) {
                y = -40 + (y * 100);
            } else {
                y = 60;
            }
            g.fillRect(x, y, 180, 80);
        }
    }

    public void drawMediumProduct(Graphics g, int x, int y) {
        if (x > 0 && y > 0) {
            if (x != 1) {
                x = -110 + (x * 200);
            } else {
                x = 90;
            }
            if (y != 1) {
                y = -76 + (y * 100);
            } else {
                y = 24;
            }
            g.fillRect(x, y, 120, 52);
        }
    }
    public void drawSmallProduct(Graphics g, int x, int y) {
        if (x > 0 && y > 0) {
            if (x != 1) {
                x = -110 + (x * 200);
            } else {
                x = 90;
            }
            if (y != 1) {
                y = -76 + (y * 100);
            } else {
                y = 24;
            }
            g.fillRect(x, y, 120, 52);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int packageStartX = 50;
        int packageStartY = 60;

        ArrayList<Product> orderList = order.getOrderPackages();

        g.setColor(Color.BLACK);
        // outlines
        g.drawLine(40, 50, 1040, 50);
        g.drawLine(40, 50, 40, 550);
        g.drawLine(1040, 50, 1040, 550);
        g.drawLine(40, 550, 1040, 550);

        // inside horizontal lines
        g.drawLine(40, 150, 1040, 150);
        g.drawLine(40, 250, 1040, 250);
        g.drawLine(40, 350, 1040, 350);
        g.drawLine(40, 450, 1040, 450);

        // inside vertical lines
        g.drawLine(240, 50, 240, 550);
        g.drawLine(440, 50, 440, 550);
        g.drawLine(640, 50, 640, 550);
        g.drawLine(840, 50, 840, 550);

        for (Product i : orderList) {
            int x = i.getX();
            int y = i.getY();

            g.setColor(i.getColor());
            if (i.getSize() == 40) {
                drawBigProduct(g, x, y);
            }
            if (i.getSize() == 30) {
                drawMediumProduct(g, x, y);
            }
            if (i.getSize() == 20) {
                drawSmallProduct(g, x, y);
            }
        }

    }
}
