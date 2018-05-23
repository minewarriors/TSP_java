package Window;

import static Core.BPPInterface.boxSize;
import BPP.DrawPanel;
import Core.Order;
import Core.Product;

import static BPPAlgorithms.Algorithms.BestFitDecreasing;
import static BPPAlgorithms.Algorithms.firstFit;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.*;
import javax.swing.Box;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class BPPMainScreen extends JFrame implements ActionListener {

    private JLabel jlXML;
    private JButton jbUploadXML;
    private JLabel jlNumberTimes;
    private JTextField jtfNumber;
    private JButton jbStart;
    private JLabel jlAlgorithm;
    private JButton jbStop;
    private JLabel jlAddProduct;
    private JTextField jtfSize;
    private JButton jbAddProduct;
    private JButton jbAddRandom;

    private final JFileChooser fc;

    JComboBox algorithmList = new JComboBox();
    Order order = new Order();
    Core.Box A = new Core.Box(boxSize);
    Core.Box B = new Core.Box(boxSize);
    Core.Box C = new Core.Box(boxSize);
    DrawPanel dp = new DrawPanel(A, B, C);

    public BPPMainScreen() {
        System.out.println("111");

        setTitle("BPP");
        setSize(1080, 800);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files", "xml");
        fc = new JFileChooser();
        fc.setFileFilter(xmlFilter);
        fc.setAcceptAllFileFilterUsed(false);

        add(dp, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        Box left1 = Box.createHorizontalBox();
        Box left2 = Box.createHorizontalBox();
        Box left3 = Box.createHorizontalBox();
        Box right1 = Box.createHorizontalBox();
        Box right2 = Box.createHorizontalBox();
        Box right3 = Box.createHorizontalBox();

        jlXML = new JLabel("Upload XML file:");
        left1.add(jlXML);
        left1.add(Box.createHorizontalStrut(30));
        jbUploadXML = new JButton("Choose file");
        jbUploadXML.addActionListener(this);
        left1.add(jbUploadXML);

        jlNumberTimes = new JLabel("Number of times:");
        right1.add(jlNumberTimes);
        right1.add(Box.createHorizontalStrut(30));
        jtfNumber = new JTextField(5);
        right1.add(jtfNumber);

        jbStart = new JButton("Start");
        jbStart.addActionListener(this);
        right2.add(jbStart);

        left2.add(Box.createHorizontalStrut(30));
        jlAlgorithm = new JLabel("BPP Algorithm:");
        left2.add(jlAlgorithm);
        left2.add(Box.createHorizontalStrut(30));
        algorithmList.addItem("First Fit");
        algorithmList.addItem("Best-fit Decreasing");
        algorithmList.addItem("Bin Completion");
        algorithmList.addItem("Own Method");
        algorithmList.addActionListener(this);
        left2.add(algorithmList);

        jbStop = new JButton("Stop");
        jbStop.addActionListener(this);
        right3.add(jbStop);

        left3.add(Box.createHorizontalStrut(30));
        jlAddProduct = new JLabel("Add product:");
        left3.add(jlAddProduct);
        left3.add(Box.createHorizontalStrut(30));
        jtfSize = new JTextField(5);
        left3.add(jtfSize);
        left3.add(Box.createHorizontalStrut(30));
        jbAddProduct = new JButton("Add");
        jbAddProduct.addActionListener(this);
        left3.add(jbAddProduct);
        left3.add(Box.createHorizontalStrut(30));
        jbAddRandom = new JButton("Add random");
        jbAddRandom.addActionListener(this);
        left3.add(jbAddRandom);

        Box leftComplete = Box.createVerticalBox();
        leftComplete.add(left1);
        leftComplete.add(Box.createVerticalStrut(30));
        leftComplete.add(left2);
        leftComplete.add(Box.createVerticalStrut(30));
        leftComplete.add(left3);
        panel1.add(leftComplete);

        Box rightComplete = Box.createVerticalBox();
        rightComplete.add(right1);
        rightComplete.add(Box.createVerticalStrut(30));
        rightComplete.add(right2);
        rightComplete.add(Box.createVerticalStrut(30));
        rightComplete.add(right3);
        panel2.add(rightComplete);

        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            //haalt informatie op uit de combo box
            String algorithm = (String) algorithmList.getSelectedItem();

            //het First Fit algoritme wordt opgehaald
            if ("First Fit".equals(algorithm)) {
                if (this.order != null) {
                    if (firstFit(this.order, A, B, C)) {
                        System.out.println("---- Succes ----");
                    } else {
                        System.out.println("---- Te weinig ruimte ----");
                    }
                } else {
                    System.out.println("Er is geen order toegevoegd!!! \n");
                }
            }

            //het First Fit algoritme wordt opgehaald
            if ("Best-fit Decreasing".equals(algorithm)) {
                if (this.order != null) {
                    if (BestFitDecreasing(this.order, A, B, C)) {
                        System.out.println("---- Succes ----");
                    } else {
                        System.out.println("---- Te weinig ruimte ----");
                    }
                } else {
                    System.out.println("Er is geen order toegevoegd!!! \n");
                }
            }
            this.order.print();

            System.out.println("box A");

            A.getProductBoxArray().forEach((a) -> {
                System.out.println(a);
            });

            System.out.println("box B");

            B.getProductBoxArray().forEach((a) -> {
                System.out.println(a);
            });

            System.out.println("box C");

            C.getProductBoxArray().forEach((a) -> {
                System.out.println(a);
            });
        }
        if (e.getSource() == jbUploadXML) {
            try {
                File xmlFile;
                int returnVal = fc.showOpenDialog(BPPMainScreen.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    xmlFile = new File(file.getAbsolutePath());

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

                            int id = parseInt(eElement.getAttribute("id"));
                            int size = parseInt(eElement.getElementsByTagName("size").item(0).getTextContent());
                            int x = parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
                            int y = parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());

                            // Use reflection to access the static member of the Color class
                            Color color;
                            String tempColor = (eElement.getElementsByTagName("color").item(0).getTextContent()).toLowerCase();
                            try {
                                Field field = Class.forName("java.awt.Color").getField(tempColor);
                                color = (Color) field.get(null);
                            } catch (Exception ex) {
                                color = BLACK;
                            }

                            Product p = new Product(id, x, y, color, size);
                            order.addToOrder(p);
                        }
                    }
                    dp.setOrder(order);
                    order.toString();
                } else {
                    System.out.println("Open command cancelled by user." + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        repaint();
    }
}
