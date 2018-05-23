package Window;

import Algoritmes.BruteForce;
import Algoritmes.Driver;
import Algoritmes.EigenMethode;
import Algoritmes.HillCliming;
import Algoritmes.Route;
import static BPPAlgorithms.Algorithms.BestFitDecreasing;
import static BPPAlgorithms.Algorithms.firstFit;
import static Core.BPPInterface.boxSize;
import Core.Order;
import Core.Product;
import SerialController.RobotController;
import SerialController.RobotControllerJpanel;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import javax.swing.Box;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bart
 */
public class RobotWindow extends JFrame implements ActionListener {

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

    ArrayList<Product> paintRoute = new ArrayList<Product>();
    private EigenMethode eigenMethode = new EigenMethode();

    private final JFileChooser fc;

    Driver driver = new Driver();

    public Driver getDriver() {
        return driver;
    }

    JComboBox bppAlgorithmList = new JComboBox();
    JComboBox tspAlgorithmList = new JComboBox();
    Order order = new Order();
    Core.Box A = new Core.Box(boxSize);
    Core.Box B = new Core.Box(boxSize);
    Core.Box C = new Core.Box(boxSize);

    RobotControllerJpanel rc = new RobotControllerJpanel();

    BPPDrawPanel bppDP = new BPPDrawPanel(A, B, C);
    DrawPanel tspDP = new DrawPanel();

    public RobotWindow() {
        setTitle("Robot Controller");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setResizable(false);

        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files", "xml");
        fc = new JFileChooser();
        fc.setFileFilter(xmlFilter);
        fc.setAcceptAllFileFilterUsed(false);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(tspDP, BorderLayout.WEST);
        panel.add(bppDP, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);

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
        bppAlgorithmList.addItem("First Fit");
        bppAlgorithmList.addItem("Best-fit Decreasing");
        bppAlgorithmList.addItem("Bin Completion");
        bppAlgorithmList.addItem("Own Method");
        bppAlgorithmList.addActionListener(this);
        left2.add(bppAlgorithmList);

        jbStop = new JButton("Stop");
        jbStop.addActionListener(this);
        right3.add(jbStop);

        left3.add(Box.createHorizontalStrut(30));
        jlAddProduct = new JLabel("TSP Algorithm");
        left3.add(jlAddProduct);
        left3.add(Box.createHorizontalStrut(30));
        tspAlgorithmList.addItem("Bruteforce");
        tspAlgorithmList.addItem("Hill Climbing");
        tspAlgorithmList.addItem("Willikeurig Beperkt");
        tspAlgorithmList.addItem("Own Algorithm");
        tspAlgorithmList.addActionListener(this);
        left3.add(tspAlgorithmList);

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

        panel3.add(rc);

        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);
        add(panel3, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStart) {
            if (this.order != null) {

                //haalt informatie op uit de combo box
                String bppAlgorithm = (String) bppAlgorithmList.getSelectedItem();
                String tspAlgorithm = (String) tspAlgorithmList.getSelectedItem();

                Route currentRoute = new Route(driver.getIntialRoute());

                //het First Fit algoritme wordt opgehaald
                if ("First Fit".equals(bppAlgorithm)) {
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

                //het Best-fit Decreasing algoritme wordt opgehaald
                if ("Best-fit Decreasing".equals(bppAlgorithm)) {
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

                //TSP Algoritmes!!
                if ("Hill Climbing".equals(tspAlgorithm)) {
                    HillCliming hillClimbing = new HillCliming();

                    System.out.println(currentRoute + " |     " + currentRoute.calculateTotalDistance());
                    hillClimbing.findShortestRoute(currentRoute);
                }
                if ("Bruteforce".equals(tspAlgorithm)) {

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
                    tspDP.setPaintingroute(paintRoute);
                    System.out.println("Op je muil met  deze Array " + paintRoute);

                }
                if ("Own Algorithm".equals(tspAlgorithm)) {
                    ArrayList<Product> producten = new ArrayList<Product>();
                    producten.addAll(driver.getIntialRoute());
                    driver.printShortestRoute(eigenMethode.FindShortestRoute(producten));
                    paintRoute.clear();
                    eigenMethode.getShortestRouteProducts().forEach(x -> {
                        paintRoute.add(x);
                    });
                    tspDP.setPaintingroute(paintRoute);
                    System.out.println("Op je muil met  deze Array " + paintRoute);
                    repaint();
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
        } else {
            System.out.println("EERST XML INLADEN!");
            paintRoute.clear();
            repaint();
        }
        if (e.getSource() == jbUploadXML) {
            try {
                File xmlFile;
                int returnVal = fc.showOpenDialog(RobotWindow.this);

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
                            bppDP.setOrder(order);
                            tspDP.setOrder(order);
                            driver.addToIntialRoute(p);
                        }
                    }
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
