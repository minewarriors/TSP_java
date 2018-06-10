/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Algoritmes.*;
import Algoritmes.BruteForce;
import Algoritmes.Driver;
import Algoritmes.EigenMethode;
import Algoritmes.Route;
import Core.Order;
import Core.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TSPWindow extends JFrame implements ActionListener {

    private JButton jbStart, stop, jbUploadXML;
    private JLabel jlAlgoritm, jlUploadXML, jlAantal;
    private JTextField jtfAantal;
    private final JFileChooser fc;
    DrawPanel dp;
    private String[] jComboboxOptions = {"Bruteforce", "Hill Climbing", "Willikeurig Beperkt", "Eigen Algoritme"};
    private Driver driver;
    private JComboBox algoritmList;
    ArrayList<Product> paintRoute = new ArrayList<Product>();
    private EigenMethode eigenMethode = new EigenMethode();
    Order order;

    public TSPWindow(Driver driver) {
        //Window settings
        setTitle("TSP simulator");
        setSize(1080, 720);
        setLayout(new FlowLayout());
        setResizable(false);

        this.driver = driver;
        // contruct and add drawPanel
        dp = new DrawPanel();
        this.add(dp);

        // combobox
        algoritmList = new JComboBox(jComboboxOptions);
        algoritmList.addActionListener(this);

        //Set up the picture.
        jlAlgoritm = new JLabel("Choose Algoritm");
        add(jlAlgoritm, BorderLayout.PAGE_END);
        add(algoritmList, BorderLayout.PAGE_START);

        // set up FileChooser + create FileFilter
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml files only", "xml");
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);

        //XML upload knop & label
        jlUploadXML = new JLabel("Upload XML File:");
        this.add(jlUploadXML);
        jbUploadXML = new JButton("Upload File");
        this.add(jbUploadXML);

        //Aantal keren algoritme knop & textfield
        jlAantal = new JLabel("Aantal keer:");
        this.add(jlAantal);
        jtfAantal = new JTextField(5);
        this.add(jtfAantal);

        //start & stop buttons
        jbStart = new JButton("Start");
        stop = new JButton("Stop");
        this.add(jbStart);

        //add actionListeners
        jbUploadXML.addActionListener(this);
        jbStart.addActionListener(this);
        stop.addActionListener(this);
    }

    public Driver getDriver() {
        return this.driver;
    }

    //dit is een aanpassing check ff Bram!
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            if (this.order != null) {
                if (e.getSource() == jtfAantal) {
                    try {
                        int input = Integer.parseInt(jtfAantal.getText());
                        for (int i = 0; i < input; i++) {

                            String Algoritm = (String) algoritmList.getSelectedItem();
                            Route currentRoute = new Route(driver.getIntialRoute());

                            // start hillclimbing algoritme
                            if ("Hill Climbing".equals(Algoritm)) {
                                HillCliming hillClimbing = new HillCliming();

                                System.out.println(currentRoute + " |     " + currentRoute.calculateTotalDistance());
                                hillClimbing.findShortestRoute(currentRoute);
                                System.out.println(hillClimbing.getShortestRoute());

                                // tekenen hillclimbing route
                                paintRoute.clear();
                                hillClimbing.getShortestRoute().forEach(x -> {
                                    paintRoute.add(x);
                                });
                                dp.setPaintingroute(paintRoute);
                                repaint();
                            }

                            // start willekeurig beperkt algoritme
                            if ("Willikeurig Beperkt".equals(Algoritm)) {
                                // de rest wordt in het dialoog afgewerkt
                                RandomSearchDialog rsd = new RandomSearchDialog(this);
                                repaint();
                            }

                            // start bruteforce algoritme
                            if ("Bruteforce".equals(Algoritm)) {

                                Instant startInstant = Instant.now();
                                BruteForce bruteforce = new BruteForce();
                                // priten alle permutaties
                                if (driver.VERBOSE_FLAG) {
                                    driver.printHeading("Route", "Distance | Shortest Distance | Permutation #");
                                } else {
                                    System.out.println("Permutation in progress ...");
                                }
                                // printen resultaten
                                driver.printResults(bruteforce, bruteforce.permutateProducten(0, currentRoute, new Route(currentRoute)));
                                driver.printDuration(startInstant);

                                //Route voor simulator painting
                                paintRoute.clear();
                                double random = 0;
                                int randomList = bruteforce.getShortestRoutes().size();
                                random = randomList * Math.random();
                                int routeRandom = (int) Math.round(random);
                                System.out.println(random);
                                bruteforce.getShortestRoutes().get(routeRandom).getProducts().forEach(x -> {
                                    paintRoute.add(x);
                                });
                                dp.setPaintingroute(paintRoute);
                                repaint();
                            }

                            // start eigen algoritme
                            if ("Eigen Algoritme".equals(Algoritm)) {
                                ArrayList<Product> producten = new ArrayList<Product>();
                                producten.addAll(driver.getIntialRoute());
                                driver.printShortestRoute(eigenMethode.FindShortestRoute(producten));

                                //tekenen gevonden route
                                paintRoute.clear();
                                eigenMethode.getShortestRouteProducts().forEach(x -> {
                                    paintRoute.add(x);
                                });
                                dp.setPaintingroute(paintRoute);
                                repaint();
                            }
                        }
                    } catch (NumberFormatException ne) {
                        System.out.println("invoer moet een getal zijn!!!");
                    }
                }
            } else {
                System.out.println("EERST XML INLADEN!");
                paintRoute.clear();
                repaint();
            }

        }

        if (e.getSource()
                == jbUploadXML) {
            try {
                // variabelen
                File xmlFile;
                int returnVal = fc.showOpenDialog(TSPWindow.this);

                //start inladen xml
                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    driver.clearIntialRoute();
                    File file = fc.getSelectedFile();
                    xmlFile = new File(file.getAbsolutePath());
                    Order order = new Order();

                    //weghalen oude route
                    driver.clearIntialRoute();

                    // laden XML file
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(xmlFile);

                    // List elements with "package" tag || Remember a Node is an element
                    NodeList nList = doc.getElementsByTagName("package");

                    // go through NodeList
                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);

                        // if NodeType is the same as ElementNode
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;
                            // Strings omzetten naar juiste datatype
                            int id = parseInt(eElement.getAttribute("id"));
                            int x = parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
                            int y = parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
                            int size = parseInt(eElement.getElementsByTagName("size").item(0).getTextContent());

                            // Gebruik reflectie om toegang te krijgen tot het statische lid van de klasse Color
                            Color color;
                            String tempColor = (eElement.getElementsByTagName("color").item(0).getTextContent()).toLowerCase();
                            try {
                                Field field = Class.forName("java.awt.Color").getField(tempColor);
                                color = (Color) field.get(null);
                            } catch (Exception ex) {
                                color = BLACK;
                            }

                            // create new Products and add them to an ArrayList
                            Product a = new Product(id, x, y, color, size);
                            order.addToOrder(a);
                            dp.setOrder(order);
                            driver.addToIntialRoute(a);
                        }
                    }
                    this.order = order;
                } else {
                    System.out.println("Open command cancelled by user." + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Product> getPaintroute() {
        return paintRoute;
    }

    public void setPaintroute(ArrayList<Product> paintRoute) {
        this.paintRoute = paintRoute;
    }

}
