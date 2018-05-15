/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

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
import javax.swing.filechooser.FileNameExtensionFilter;

public class TSPWindow extends JFrame implements ActionListener {

    private JButton start, stop, add, random, jbUploadXML;
    private JTextField x, y, name, numberOfTimes;
    private JLabel jlAlgoritm, jlAdd, jlNumberOfTimes, jlUploadXML;
    private final JFileChooser fc;
    private DrawPanel dp;
    private String[] jComboboxOptions = {"Bruteforce","Bellman","Willikeurig Beperkt", "Eigen Algoritme"};
    

    public TSPWindow() {
        //Window settings
        setTitle("TSP simulator");
        setSize(1080, 720);
        setLayout(new FlowLayout());
        setResizable(false);

        System.out.println("monkaS");
        // contruct and add drawPanel
        dp = new DrawPanel();
        add(dp);

        // combobox
        JComboBox algoritmList = new JComboBox(jComboboxOptions);
        algoritmList.addActionListener(this);
        
        //Set up the picture.
        jlAlgoritm = new JLabel();
        jlAlgoritm.setFont(jlAlgoritm.getFont().deriveFont(Font.ITALIC));
        add(algoritmList, BorderLayout.PAGE_START);
        add(jlAlgoritm, BorderLayout.PAGE_END);
        
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
                        }
                    }
                } else {
                    System.out.println("Open command cancelled by user." + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
