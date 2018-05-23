package Window;

import Core.Box;
import static Core.BPPInterface.boxSize;
import Core.Order;
import Core.Product;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BPPDrawPanel extends JPanel {

    private Order order;
    private Box a;
    private Box b;
    private Box c;
    private int y;
    private ArrayList<Product> orderList;
    private boolean check = true;

    public BPPDrawPanel(Box boxA, Box boxB, Box boxC) {
        this.a = boxA;
        this.b = boxB;
        this.c = boxC;
        this.order = null;
        this.setPreferredSize(new Dimension(980, 600));
        setBackground(Color.WHITE);

    }

    public void setOrder(Order order) {
        this.order = order;
        orderList = order.getOrderPackages();
        repaint();
    }

    //volgende methodes zorgen ervoor dat pakketten in een rij worden getekent op volgorde van id
    public void drawBigProduct(Graphics g, int id) {
        if (id > 0) {
            int x = 0;
            if (id == 1) {
                x = 12;
            }
            if (id == 2) {
                x = 224;
            }
            if (id == 3) {
                x = 436;
            }
            if (id == 4) {
                x = 648;
            }
            if (id == 5) {
                x = 860;
            }
            g.fillRect(x, 30, 200, 200);
        }
    }

    public void drawMediumProduct(Graphics g, int id) {
        if (id > 0) {
            int x = 0;
            if (id == 1) {
                x = 12;
            }
            if (id == 2) {
                x = 224;
            }
            if (id == 3) {
                x = 436;
            }
            if (id == 4) {
                x = 648;
            }
            if (id == 5) {
                x = 860;
            }
            g.fillRect(x, 30, 200, 150);
        }
    }

    public void drawSmallProduct(Graphics g, int id) {
        if (id > 0) {
            int x = 0;
            if (id == 1) {
                x = 12;
            }
            if (id == 2) {
                x = 224;
            }
            if (id == 3) {
                x = 436;
            }
            if (id == 4) {
                x = 648;
            }
            if (id == 5) {
                x = 860;
            }
            g.fillRect(x, 30, 200, 100);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (order != null) {

            //kijken welke methode aangeroepen moet worden op grootte
            for (Product p : orderList) {

                check = true;

                a.getProductBoxArray().forEach((product) -> {
                    if (product == p) {
                        check = false;
                    }
                });
                b.getProductBoxArray().forEach((product) -> {
                    if (product == p) {
                        check = false;
                    }
                });
                c.getProductBoxArray().forEach((product) -> {
                    if (product == p) {
                        check = false;
                    }
                });

                if (check) {
                    int id = p.getProductId();

                    g.setColor(p.getColor());
                    if (p.getSize() == 40) {
                        drawBigProduct(g, id);
                    }
                    if (p.getSize() == 30) {
                        drawMediumProduct(g, id);
                    }
                    if (p.getSize() == 20) {
                        drawSmallProduct(g, id);
                    }
                }
            }

            if (a != null) {
                y = 550;
                a.getProductBoxArray().forEach((productA) -> {

                    g.setColor(productA.getColor());

                    if (productA.getSize() == 40) {

                        g.fillRect(140, (y - 200), 200, 200);
                        y -= 200;

                    }

                    if (productA.getSize() == 30) {

                        g.fillRect(140, (y - 150), 200, 150);
                        y -= 150;

                    }

                    if (productA.getSize() == 20) {

                        g.fillRect(140, (y - 100), 200, 100);
                        y -= 100;

                    }

                });
            }
            if (b != null) {
                y = 550;
                b.getProductBoxArray().forEach((productB) -> {

                    g.setColor(productB.getColor());

                    if (productB.getSize() == 40) {

                        g.fillRect(440, (y - 200), 200, 200);
                        y -= 200;

                    }

                    if (productB.getSize() == 30) {

                        g.fillRect(440, (y - 150), 200, 150);
                        y -= 150;

                    }

                    if (productB.getSize() == 20) {

                        g.fillRect(440, (y - 100), 200, 100);
                        y -= 100;

                    }

                });
            }
            if (c != null) {
                y = 550;
                c.getProductBoxArray().forEach((productC) -> {

                    g.setColor(productC.getColor());

                    if (productC.getSize() == 40) {

                        g.fillRect(740, (y - 200), 200, 200);
                        y -= 200;

                    }

                    if (productC.getSize() == 30) {

                        g.fillRect(740, (y - 150), 200, 150);
                        y -= 150;

                    }

                    if (productC.getSize() == 20) {

                        g.fillRect(740, (y - 100), 200, 100);
                        y -= 100;

                    }

                });
            }
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(
                new BasicStroke(5));
        //box 1
        g2.setColor(Color.black);

        g2.drawLine(
                135, 250, 135, 550);
        g2.drawLine(
                345, 552, 135, 552);
        g2.drawLine(
                345, 250, 345, 550);

        //box 2
        g2.drawLine(
                435, 250, 435, 550);
        g2.drawLine(
                645, 552, 435, 552);
        g2.drawLine(
                645, 250, 645, 550);

        //box 3
        g2.drawLine(
                735, 250, 735, 550);
        g2.drawLine(
                945, 552, 735, 552);
        g2.drawLine(
                945, 250, 945, 550);
    }
}
