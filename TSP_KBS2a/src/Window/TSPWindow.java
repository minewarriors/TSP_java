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
    private JFileChooser jFileChooser;

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

        jFileChooser = new JFileChooser();
        this.add(jFileChooser);
        jFileChooser.addActionListener(this);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        /*
        jbUploadXML = new JButton("Upload File");
        this.add(jbUploadXML);
        jbUploadXML.addActionListener(this);
        */
        System.out.println("Window has been build");
    }

    public void setVisile(TSPWindow TSPwindow) {
        TSPwindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jFileChooser) {
            System.out.println("actionlistener bf4 try{}");
            try {
                File xmlFile;
                int result = jFileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    xmlFile = new File(selectedFile.getAbsolutePath());

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

                            System.out.println("package id : " + eElement.getAttribute("id"));
                            System.out.println("Size : " + eElement.getElementsByTagName("size").item(0).getTextContent());
                            System.out.println("Colour : " + eElement.getElementsByTagName("colour").item(0).getTextContent());
                            System.out.println("Number : " + eElement.getElementsByTagName("number").item(0).getTextContent());

                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
