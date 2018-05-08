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
        this.setVisible(true);

        //XML uploaden
        jlUploadXML = new JLabel("Upload XML File:");
        this.add(jlUploadXML);
        jbUploadXML = new JButton("Upload File");
        this.add(jbUploadXML);
        
        System.out.println("Window has been build");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbUploadXML) {
            System.out.println("actionlistener bf4 try{}");
            try {
                File xmlFile = new File("C:\\Users\\Bram ten Brinke\\Documents\\Nieuwe map\\TSP_KBS2a\\src\\TestXML");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);

                //optional, but recommended
                //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                doc.getDocumentElement().normalize();

                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                NodeList nList = doc.getElementsByTagName("package");

                System.out.println("----------------------------");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("Staff id : " + eElement.getAttribute("id"));
                        System.out.println("First Name : " + eElement.getElementsByTagName("size").item(0).getTextContent());
                        System.out.println("Last Name : " + eElement.getElementsByTagName("colour").item(0).getTextContent());
                        System.out.println("Nick Name : " + eElement.getElementsByTagName("number").item(0).getTextContent());

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
