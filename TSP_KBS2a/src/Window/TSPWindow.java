/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Algoritmes.*;
import Core.*;
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
import java.util.Timer;
import java.util.TimerTask;

public class TSPWindow extends JFrame implements ActionListener {

    private JButton jbStart, stop, jbUploadXML;
    private JLabel jlAlgoritm, jl, jlUploadXML;
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

        System.out.println("ugthh");

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

        //
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            if (this.order != null) {
                String Algoritm = (String) algoritmList.getSelectedItem();
                Route currentRoute = new Route(driver.getIntialRoute());
                if ("Hill Climbing".equals(Algoritm)) {
                    HillCliming hillClimbing = new HillCliming();

                    System.out.println(currentRoute + " |     " + currentRoute.calculateTotalDistance());
                    hillClimbing.findShortestRoute(currentRoute);
                    System.out.println(hillClimbing.getShortestRoute());

                    paintRoute.clear();

                    hillClimbing.getShortestRoute().forEach(x -> {
                        paintRoute.add(x);
                    });
                    dp.setPaintingroute(paintRoute);

                    repaint();
                    System.out.println("Dit is de paint route" + paintRoute);

                }
                if ("Willikeurig Beperkt".equals(Algoritm)) {
                    RandomSearchDialog rsd = new RandomSearchDialog(this);

                }
                if ("Bruteforce".equals(Algoritm)) {

                    Instant startInstant = Instant.now();
                    BruteForce bruteforce = new BruteForce();

                    if (driver.VERBOSE_FLAG) {
                        driver.printHeading("Route", "Distance | Shortest Distance | Permutation #");
                    } else {
                        System.out.println("Permutation in progress ...");
                    }
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
                        repaint();
                    });
                    dp.setPaintingroute(paintRoute);
                    System.out.println("Op je muil met  deze Array " + paintRoute);

                }
                if ("Eigen Algoritme".equals(Algoritm)) {
                    ArrayList<Product> producten = new ArrayList<Product>();
                    producten.addAll(driver.getIntialRoute());
                    driver.printShortestRoute(eigenMethode.FindShortestRoute(producten));
                    paintRoute.clear();
                    eigenMethode.getShortestRouteProducts().forEach(x -> {
                        paintRoute.add(x);
                    });
                    dp.setPaintingroute(paintRoute);
                    System.out.println("Op je muil met  deze Array " + paintRoute);
                    repaint();
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
                // variables
                File xmlFile;
                int returnVal = fc.showOpenDialog(TSPWindow.this);

                //on click filechooser APPROVE
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    xmlFile = new File(file.getAbsolutePath());
                    Order order = new Order();

                    //Clear previous order
                    driver.clearIntialRoute();

                    // loading XML file
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
                            // converting String types
                            int id = parseInt(eElement.getAttribute("id"));
                            int x = parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
                            int y = parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
                            int size = parseInt(eElement.getElementsByTagName("size").item(0).getTextContent());

                            // Use reflection to access the static member of the Color class
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
