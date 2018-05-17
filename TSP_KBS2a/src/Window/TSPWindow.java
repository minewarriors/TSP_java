/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Algoritmes.BruteForce;
import Algoritmes.Driver;
import Algoritmes.Route;
import Core.Order;
import Core.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.*;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.filechooser.FileNameExtensionFilter;

public class TSPWindow extends JFrame implements ActionListener {

    private JButton jbStart, stop, jbUploadXML;
    private JLabel jlAlgoritm, jl, jlUploadXML;
    private final JFileChooser fc;
    private DrawPanel dp;
    private String[] jComboboxOptions = {"Bruteforce", "Bellman", "Willikeurig Beperkt", "Eigen Algoritme"};
    private Driver driver;
    private JComboBox algoritmList;
    private Order order;

    public TSPWindow(Driver driver) {
        //Window settings
        setTitle("TSP simulator");
        setSize(1080, 720);
        setLayout(new FlowLayout());
        setResizable(false);

        this.driver = driver;
        System.out.println("monkaS");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            String Algoritm = (String) algoritmList.getSelectedItem();

            if ("Bruteforce" == Algoritm) {
                if (this.order != null) {
                    Instant startInstant = Instant.now();
                    BruteForce bruteforce = new BruteForce();

                    Route currentRoute = new Route(driver.getIntialRoute());

                    if (driver.VERBOSE_FLAG) {
                        driver.printHeading("Route", "Distance | Shortest Distance | Permutation #");
                    } else {
                        System.out.println("Permutation in progress ...");
                    }
                    driver.printResults(bruteforce, bruteforce.permutateProducten(0, currentRoute, new Route(currentRoute)));
                    driver.printDuration(startInstant);
                } else {
                    System.out.println("EERST XML INLADEN!");
                }
            }
        }

        if (e.getSource() == jbUploadXML) {
            try {
                // variables
                File xmlFile;
                int returnVal = fc.showOpenDialog(TSPWindow.this);

                //on click filechooser APPROVE
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    xmlFile = new File(file.getAbsolutePath());
                    Order order = new Order();

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

}
