/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

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
    //private JCombobox algoritm;
    private JTextField x, y, name, numberOfTimes;
    private JLabel jlAlgoritm, jlAdd, jlNumberOfTimes, jlUploadXML;

    public TSPWindow() {
        //Window settings
        setSize(1080, 720);
        setTitle("Retrieval & Storage System");
        setLayout(new FlowLayout());

        // testing purposes
        this.setVisible(false);

        //XML uploaden
        jlUploadXML = new JLabel("Upload XML File:");
        this.add(jlUploadXML);
        jbUploadXML = new JButton("Upload File");
        this.add(jbUploadXML);

    }

    public void setVisile(TSPWindow TSPwindow) {
        TSPwindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbUploadXML) {
            try {
                File xmlFile = new File("C:\\Users\\Bram ten Brinke\\Documents\\Nieuwe map\\TSP_KBS2a\\src\\TestXML");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);

                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                NodeList nList = doc.getElementsByTagName("package");

                System.out.println("----------------------------");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("Package id : " + eElement.getAttribute("id"));
                        System.out.println("Size : " + eElement.getElementsByTagName("Size").item(0).getTextContent());
                        System.out.println("Colour : " + eElement.getElementsByTagName("Colour").item(0).getTextContent());
                        System.out.println("Number : " + eElement.getElementsByTagName("Number").item(0).getTextContent());

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
