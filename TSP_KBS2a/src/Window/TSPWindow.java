/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Core.Order;
import Core.Product;
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

public class TSPWindow extends JFrame implements ActionListener {

    private JButton start, stop, add, random, jbUploadXML;
    private JTextField x, y, name, numberOfTimes;
    private JLabel jlAlgoritm, jlAdd, jlNumberOfTimes, jlUploadXML;
    private final JFileChooser fc;

    public TSPWindow() {
        //Window settings
        setTitle("TSP simulator");
        setSize(1080, 720);
        setLayout(new FlowLayout());
        setResizable(false);

        //TESTCODE ||| TO BE REMOVED LATER
        System.out.println("cmmonbruh");

        Product p1 = new Product(1, 1, 1, BLUE, 40);
        Product p2 = new Product(2, 2, 2, RED, 40);
        Product p3 = new Product(3, 3, 3, BLUE, 30);
        Product p4 = new Product(4, 4, 4, RED, 30);
        Product p5 = new Product(4, 5, 5, RED, 20);

        Order o1 = new Order();
        o1.addToOrder(p1);
        o1.addToOrder(p2);
        o1.addToOrder(p3);
        o1.addToOrder(p4);
        o1.addToOrder(p5);
        // EINDE TESTCODE ||| TO BE REMOVED LATER

        // contruct and add drawPanel
        DrawPanel dp = new DrawPanel(o1);
        add(dp);

        fc = new JFileChooser();

        //XML upload knop & label
        jlUploadXML = new JLabel("Upload XML File:");
        this.add(jlUploadXML);

        jbUploadXML = new JButton("Upload File");
        this.add(jbUploadXML);

        //add actionListeners
        jbUploadXML.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

                    System.out.println("----------------------------");
                    // go through NodeList
                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);

                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
                        // if NodeType is the same as ElementNode
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            System.out.println("package id : " + eElement.getAttribute("id"));
                            System.out.println("Size : " + eElement.getElementsByTagName("size").item(0).getTextContent());
                            System.out.println("Colour : " + eElement.getElementsByTagName("colour").item(0).getTextContent());
                            System.out.println("Number : " + eElement.getElementsByTagName("number").item(0).getTextContent());
                        }
                    }
                    System.out.println(order);
                } else {
                    System.out.println("Open command cancelled by user." + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
