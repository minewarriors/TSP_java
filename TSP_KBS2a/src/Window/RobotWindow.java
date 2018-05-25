package Window;

import Algoritmes.BruteForce;
import Algoritmes.Driver;
import Algoritmes.EigenMethode;
import Algoritmes.HillCliming;
import Algoritmes.Route;
import static BPPAlgorithms.Algorithms.BestFitDecreasing;
import static BPPAlgorithms.Algorithms.BinCompletion;
import static BPPAlgorithms.Algorithms.OwnMethod;
import static BPPAlgorithms.Algorithms.firstFit;
import static Core.BPPInterface.boxSize;
import Core.Order;
import Core.Product;
import SerialController.DataLogger;
import SerialController.RobotControllerJpanel;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.BLACK;
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

    private JLabel jlXML, jlBPPAlgorithm, jlTSPAlgorithm;
    private JButton jbUploadXML, jbCalculate, jbReset, jbShutdown;

    private final JFileChooser fc;

    //Alle benodigde objecten aanmaken
    EigenMethode eigenMethode = new EigenMethode();

    Driver driver = new Driver();
    ArrayList<Product> paintRoute = new ArrayList<>();

    JComboBox bppAlgorithmList = new JComboBox();
    JComboBox tspAlgorithmList = new JComboBox();
    Order order = new Order();
    Core.Box A = new Core.Box(boxSize);
    Core.Box B = new Core.Box(boxSize);
    Core.Box C = new Core.Box(boxSize);

    //Teken panels en robotcontroller aanmaken
    RobotControllerJpanel rc = new RobotControllerJpanel(paintRoute, A, B, C);
    BPPDrawPanel bppDP = new BPPDrawPanel(A, B, C);
    DrawPanel tspDP = new DrawPanel();

    public RobotWindow() {
        //Het scherm full-screen maken en layout defineren
        setTitle("Robot Controller");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setResizable(false);

        //FileChooser aanmaken om XML files up te loaden
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files", "xml");
        fc = new JFileChooser();
        fc.setFileFilter(xmlFilter);
        fc.setAcceptAllFileFilterUsed(false);

        //JPanels maken om de tekenpanels en knoppen in te verdelen
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(new BorderLayout());

        //teken pannels toevoegen
        panel.add(tspDP, BorderLayout.WEST);
        panel.add(bppDP, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);

        //Boxen aanmaken om daar alle benodigde knoppen in te stoppen
        Box left1 = Box.createHorizontalBox();
        Box left2 = Box.createHorizontalBox();
        Box left3 = Box.createHorizontalBox();
        Box left4 = Box.createHorizontalBox();
        Box left5 = Box.createHorizontalBox();

        //alle knoppen benoemen en toevoegen aan de boxes
        jlXML = new JLabel("Upload XML file:");
        left1.add(jlXML);
        left1.add(Box.createHorizontalStrut(30));
        jbUploadXML = new JButton("Choose file");
        jbUploadXML.addActionListener(this);
        left1.add(jbUploadXML);

        jbCalculate = new JButton("Calculate");
        jbCalculate.addActionListener(this);
        left4.add(jbCalculate);

        left2.add(Box.createHorizontalStrut(30));
        jlBPPAlgorithm = new JLabel("BPP Algorithm:");
        left2.add(jlBPPAlgorithm);
        left2.add(Box.createHorizontalStrut(30));
        bppAlgorithmList.addItem("First Fit");
        bppAlgorithmList.addItem("Best-fit Decreasing");
        bppAlgorithmList.addItem("Bin Completion");
        bppAlgorithmList.addItem("Own Method");
        bppAlgorithmList.addActionListener(this);
        left2.add(bppAlgorithmList);

        left4.add(Box.createHorizontalStrut(30));
        jbReset = new JButton("Reset");
        jbReset.addActionListener(this);
        left4.add(jbReset);

        jbShutdown = new JButton("Shutdown");
        jbShutdown.addActionListener(this);
        left5.add(jbShutdown);

        left3.add(Box.createHorizontalStrut(30));
        jlTSPAlgorithm = new JLabel("TSP Algorithm");
        left3.add(jlTSPAlgorithm);
        left3.add(Box.createHorizontalStrut(30));
        tspAlgorithmList.addItem("Bruteforce");
        tspAlgorithmList.addItem("Hill Climbing");
        tspAlgorithmList.addItem("Willekeurig Beperkt");
        tspAlgorithmList.addItem("Own Algorithm");
        tspAlgorithmList.addActionListener(this);
        left3.add(tspAlgorithmList);

        //een verticale box maken om alle horizontale boxes aan toe te voegen zodat het 1 geheel wordt en daarna aan de juiste Jpanel toevoegen
        Box leftComplete = Box.createVerticalBox();
        leftComplete.add(left1);
        leftComplete.add(Box.createVerticalStrut(30));
        leftComplete.add(left2);
        leftComplete.add(Box.createVerticalStrut(30));
        leftComplete.add(left3);
        leftComplete.add(Box.createVerticalStrut(30));
        leftComplete.add(left4);
        leftComplete.add(Box.createVerticalStrut(100));
        leftComplete.add(left5);
        panel1.add(leftComplete);

        panel2.add(rc);

        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.WEST);
    }

    public Driver getDriver() {
        return driver;
    }

    public void actionPerformed(ActionEvent e) {
        //De knop om de algoritmes te berekenen
        if (e.getSource() == jbCalculate) {
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

                //het Bin Completion algoritme wordt opgehaald
                if ("Bin Completion".equals(bppAlgorithm)) {
                    if (this.order != null) {
                        if (BinCompletion(this.order, A, B, C)) {
                            System.out.println("---- Succes ----");
                        } else {
                            System.out.println("---- Te weinig ruimte ----");
                        }
                    } else {
                        System.out.println("Er is geen order toegevoegd!!! \n");
                    }
                }

                //het Own Method algoritme wordt opgehaald
                if ("Own Method".equals(bppAlgorithm)) {
                    if (this.order != null) {
                        if (OwnMethod(this.order, A, B, C)) {
                            System.out.println("---- Succes ----");
                        } else {
                            System.out.println("---- Te weinig ruimte ----");
                        }
                    } else {
                        System.out.println("Er is geen order toegevoegd!!! \n");
                    }
                }

                //TSP Algoritmes
                //het Hill Climbing algoritme wordt opgehaald
                if ("Hill Climbing".equals(tspAlgorithm)) {
                    HillCliming hillClimbing = new HillCliming();

                    System.out.println(currentRoute + " |     " + currentRoute.calculateTotalDistance());
                    hillClimbing.findShortestRoute(currentRoute);
                    System.out.println(hillClimbing.getShortestRoute());

                    paintRoute.clear();

                    hillClimbing.getShortestRoute().forEach(x -> {
                        paintRoute.add(x);
                    });
                    tspDP.setPaintingroute(paintRoute);

                    repaint();
                    System.out.println("Dit is de paint route" + paintRoute);

                }

                //het Willekeurig Beperkt algoritme wordt opgehaald
                if ("Willekeurig Beperkt".equals(tspAlgorithm)) {
                    RobotRSDialog rsd = new RobotRSDialog(this);

                }

                //het Bruteforce algoritme wordt opgehaald
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
                    int routeRandom = (int) Math.round(random) - 1;
                    System.out.println(random);
                    if (bruteforce.getShortestRoutes().size() > 0) {
                        bruteforce.getShortestRoutes().get(routeRandom).getProducts().forEach(x -> {
                            paintRoute.add(x);
                            repaint();
                        });
                    }
                    tspDP.setPaintingroute(paintRoute);

                }

                //het Own Algorithm algoritme wordt opgehaald
                if ("Own Algorithm".equals(tspAlgorithm)) {
                    ArrayList<Product> producten = new ArrayList<Product>();
                    producten.addAll(driver.getIntialRoute());
                    driver.printShortestRoute(eigenMethode.FindShortestRoute(producten));
                    paintRoute.clear();
                    eigenMethode.getShortestRouteProducts().forEach(x -> {
                        paintRoute.add(x);
                    });
                    tspDP.setPaintingroute(paintRoute);
                    repaint();
                }
                DataLogger.addData("Dit is de route" + paintRoute);
                rc.updateMonitor();

                this.order.print();
                //Hier worden de boxen met daarin de producten geprint.
                System.out.println("box A:");

                A.getProductBoxArray().forEach((a) -> {
                    System.out.println(a);
                });

                System.out.println("box B:");

                B.getProductBoxArray().forEach((a) -> {
                    System.out.println(a);
                });

                System.out.println("box C:");

                C.getProductBoxArray().forEach((a) -> {
                    System.out.println(a);

                });
            } else {
                //Als er geen xml is ingeladen wordt er een melding aan de datalogger toegevoegd
                DataLogger.addData("EERST XML INLADEN!");
                rc.updateMonitor();
                paintRoute.clear();
                repaint();
            }
        }
        //Een reset knop die alle array's met de berekeningen leeg haald
        if (e.getSource() == jbReset) {
            A.getProductBoxArray().clear();
            B.getProductBoxArray().clear();
            C.getProductBoxArray().clear();
            paintRoute.clear();
            repaint();
        }
        //knop op een xml bestand up te loaden
        if (e.getSource() == jbUploadXML) {
            try {
                File xmlFile;
                int returnVal = fc.showOpenDialog(RobotWindow.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    driver.clearIntialRoute();
                    A.getProductBoxArray().clear();
                    B.getProductBoxArray().clear();
                    C.getProductBoxArray().clear();
                    paintRoute.clear();
                    this.order.getOrderPackages().clear();
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

                            //haalt de elementen op uit de xml en gaat ze daarna toevoegen aan de order array
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
        //een afsluit knop om het full-screen scherm af te sluiten en alle array's leeg worden gegooid om daarna opnieuw te kunnen beginnen
        if (e.getSource() == jbShutdown) {
            this.order.getOrderPackages().clear();
            A.getProductBoxArray().clear();
            B.getProductBoxArray().clear();
            C.getProductBoxArray().clear();
            driver.clearIntialRoute();
            paintRoute.clear();
            repaint();
            setVisible(false);
        }
        repaint();
    }
}
