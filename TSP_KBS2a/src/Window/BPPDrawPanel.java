package Window;

import Core.Box;
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
    private double grootte = 0.75;

    public BPPDrawPanel(Box boxA, Box boxB, Box boxC) {
        this.a = boxA;
        this.b = boxB;
        this.c = boxC;
        this.order = null;
        this.setPreferredSize(new Dimension((int) (1120 * grootte), (int) (600 * grootte)));
        setBackground(Color.WHITE);

    }

    public void setOrder(Order order) {
        this.order = order;
        orderList = order.getOrderPackages();
        repaint();
    }

    //volgende methodes zorgen ervoor dat pakketten in een rij worden getekent op volgorde van id
    public void drawBigProduct(Graphics g, int id) {
        int x = 0;
        if (id > 0) {
            if (id <= 5) {
                x = -200 + (id * 212);
                g.fillRect((int) (x * grootte), (int) (30 * grootte), (int) (200 * grootte), (int) (200 * grootte));
            } else {
                x = -1260 + (id * 212);
                g.fillRect((int) (x * grootte), (int) (580 * grootte), (int) (200 * grootte), (int) (200 * grootte));
            }
        }
    }

    public void drawMediumProduct(Graphics g, int id) {
        int x = 0;
        if (id > 0) {
            if (id <= 5) {
                x = -200 + (id * 212);
                g.fillRect((int) (x * grootte), (int) (30 * grootte), (int) (200 * grootte), (int) (150 * grootte));
            } else {
                x = -1260 + (id * 212);
                g.fillRect((int) (x * grootte), (int) (580 * grootte), (int) (200 * grootte), (int) (150 * grootte));
            }
        }
    }

    public void drawSmallProduct(Graphics g, int id) {
        int x = 0;
        if (id > 0) {
            if (id <= 5) {
                x = -200 + (id * 212);
                g.fillRect((int) (x * grootte), (int) (30 * grootte), (int) (200 * grootte), (int) (100 * grootte));
            } else {
                x = -1260 + (id * 212);
                g.fillRect((int) (x * grootte), (int) (580 * grootte), (int) (200 * grootte), (int) (100 * grootte));
            }
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
                y = (int) (550 * grootte);
                a.getProductBoxArray().forEach((productA) -> {

                    g.setColor(productA.getColor());

                    if (productA.getSize() == 40) {
                        g.fillRect((int) (140 * grootte), (int) (y - (200 * grootte)), (int) (200 * grootte), (int) (200 * grootte));
                        y -= (int) (200 * grootte);
                    }
                    if (productA.getSize() == 30) {
                        g.fillRect((int) (140 * grootte), (int) (y - (150 * grootte)), (int) (200 * grootte), (int) (150 * grootte));
                        y -= (int) (150 * grootte);
                    }
                    if (productA.getSize() == 20) {
                        g.fillRect((int) (140 * grootte), (int) (y - (100 * grootte)), (int) (200 * grootte), (int) (100 * grootte));
                        y -= (int) (100 * grootte);
                    }
                });
            }
            if (b != null) {
                y = (int) (550 * grootte);
                b.getProductBoxArray().forEach((productB) -> {

                    g.setColor(productB.getColor());

                    if (productB.getSize() == 40) {
                        g.fillRect((int) (440 * grootte), (int) (y - (200 * grootte)), (int) (200 * grootte), (int) (200 * grootte));
                        y -= (int) (200 * grootte);
                    }
                    if (productB.getSize() == 30) {
                        g.fillRect((int) (440 * grootte), (int) (y - (150 * grootte)), (int) (200 * grootte), (int) (150 * grootte));
                        y -= (int) (150 * grootte);
                    }
                    if (productB.getSize() == 20) {
                        g.fillRect((int) (440 * grootte), (int) (y - (100 * grootte)), (int) (200 * grootte), (int) (100 * grootte));
                        y -= (int) (100 * grootte);
                    }
                });
            }
            if (c != null) {
                y = (int) (550 * grootte);
                c.getProductBoxArray().forEach((productC) -> {

                    g.setColor(productC.getColor());

                    if (productC.getSize() == 40) {
                        g.fillRect((int) (740 * grootte), (int) (y - (200 * grootte)), (int) (200 * grootte), (int) (200 * grootte));
                        y -= (int) (200 * grootte);
                    }
                    if (productC.getSize() == 30) {
                        g.fillRect((int) (740 * grootte), (int) (y - (150 * grootte)), (int) (200 * grootte), (int) (150 * grootte));
                        y -= (int) (150 * grootte);
                    }
                    if (productC.getSize() == 20) {
                        g.fillRect((int) (740 * grootte), (int) (y - (100 * grootte)), (int) (200 * grootte), (int) (100 * grootte));
                        y -= (int) (100 * grootte);
                    }
                });
            }
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.black);

        //box 1
        g2.drawLine((int) (135 * grootte), (int) (250 * grootte), (int) (135 * grootte), (int) (550 * grootte));
        g2.drawLine((int) (345 * grootte), (int) (552 * grootte), (int) (135 * grootte), (int) (552 * grootte));
        g2.drawLine((int) (345 * grootte), (int) (250 * grootte), (int) (345 * grootte), (int) (550 * grootte));

        //box 2
        g2.drawLine((int) (435 * grootte), (int) (250 * grootte), (int) (435 * grootte), (int) (550 * grootte));
        g2.drawLine((int) (645 * grootte), (int) (552 * grootte), (int) (435 * grootte), (int) (552 * grootte));
        g2.drawLine((int) (645 * grootte), (int) (250 * grootte), (int) (645 * grootte), (int) (550 * grootte));

        //box 3
        g2.drawLine((int) (735 * grootte), (int) (250 * grootte), (int) (735 * grootte), (int) (550 * grootte));
        g2.drawLine((int) (945 * grootte), (int) (552 * grootte), (int) (735 * grootte), (int) (552 * grootte));
        g2.drawLine((int) (945 * grootte), (int) (250 * grootte), (int) (945 * grootte), (int) (550 * grootte));
    }
}
