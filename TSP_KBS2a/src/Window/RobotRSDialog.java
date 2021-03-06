/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Algoritmes.Driver;
import Algoritmes.Route;
import Algoritmes.WillekeurigBeperkt;
import Core.Product;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Bram ten Brinke
 */
public class RobotRSDialog extends JDialog implements ActionListener {

    private JLabel jlNumber, jlWarning;
    private JButton jbConfirm, jbCancel;
    private JTextArea jTextArea;
    private RobotWindow rw;
    private ArrayList<Product> shortestRoute = new ArrayList<Product>();

    public RobotRSDialog(RobotWindow rw) {
        setTitle("Willekeurig zoeken instellen");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        this.rw = rw;

        jlNumber = new JLabel("Aantal simulaties:");
        jbConfirm = new JButton("Start Simulatie");
        jbCancel = new JButton("Annuleren");
        jTextArea = new JTextArea();
        jlWarning = new JLabel();

        this.add(jlNumber);
        this.add(jTextArea);
        this.add(jbCancel);
        this.add(jbConfirm);
        this.add(jlWarning);

        jbConfirm.addActionListener(this);
        jbCancel.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            dispose();
        }
        if (e.getSource() == jbConfirm) {
            try {
                String contentsTextArea = jTextArea.getText().trim();
                int intContent = Integer.parseInt(contentsTextArea);
                System.out.println(intContent);

                if (intContent > 0) {
                    if (rw.order != null) {
                        Instant startInstant = Instant.now();
                        WillekeurigBeperkt wlbp = new WillekeurigBeperkt();
                        Driver d = rw.getDriver();
                        Route currentRoute = new Route(d.getIntialRoute());

                        if (d.VERBOSE_FLAG) {
                            d.printHeading("Route", "Distance | Shortest Distance | Permutation #");
                        } else {
                            System.out.println("Permutation in progress ...");
                        }

                        d.printResults(wlbp, wlbp.permutateProducts(intContent, currentRoute, new Route(currentRoute)));
                        d.printDuration(startInstant);

                        //Route voor simulator painting
                        rw.paintRoute.clear();
                        double random = 0;
                        int randomList = wlbp.getShortestRoutes().size();
                        random = randomList * Math.random();
                        int routeRandom = (int) Math.round(random);
                        System.out.println(random);
                        wlbp.getShortestRoutes().get(routeRandom).getProducts().forEach(x -> {
                            rw.paintRoute.add(x);
                        });
                        rw.tspDP.setPaintingroute(rw.paintRoute);
                        rw.repaint();
                        dispose();
                    }
                }
            } catch (NumberFormatException ex) {
                jlWarning.setText("Vul een aantal in");
                jlWarning.setForeground(Color.RED);
            }
        }
    }

    public ArrayList<Product> getShortestRoute() {
        return shortestRoute;
    }

}
