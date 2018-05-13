/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bram ten Brinke
 */
public class UploadXMLDialog extends JDialog implements ActionListener{
    private JFileChooser jFileChooser;
    
    public UploadXMLDialog() {
        this.setVisible(true);
        
        setSize(600, 400);
        setTitle("Select an XML file to upload");
        setLayout(new FlowLayout());
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jFileChooser) {
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
