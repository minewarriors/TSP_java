package SerialController;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.PrintWriter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JOptionPane;

public class SerialConnection extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private SerialPort comPort;
    private final String comPortName = "COM3";
    private final int baudRate = 9600;
    static PrintWriter outPut;
    private final JPanel contentPane;
    private boolean connected = false;
    JButton btnConnect = new JButton("Connect");
    JButton btnDisconnect = new JButton("Disconnect");
    JButton btStart = new JButton("Start");
    JButton btEStop = new JButton("Emergency Stop");
    JLabel lblStatus = new JLabel("Status:");
    PictureCheck topBar1 = new PictureCheck();
    
    public SerialConnection() {
        setTitle("Serial Communication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1080, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 0, 0, 0));
        btnConnect.setBackground(Color.green);
        btnDisconnect.setBackground(Color.red);

        JPanel upperBar = new JPanel();
        contentPane.add(upperBar);
       
        upperBar.add(topBar1);
        topBar1.setVisible(true);

        upperBar.add(btnDisconnect);
        btnDisconnect.addActionListener(this);
        upperBar.add(btnConnect);
        btnConnect.addActionListener(this);
        
        JPanel middlePanel = new JPanel();
        contentPane.add(middlePanel);
        
        middlePanel.add(btStart);
        btStart.addActionListener(this);

        middlePanel.add(btEStop);
        btEStop.addActionListener(this);

        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
        contentPane.add(lblStatus);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConnect) {
            comPort = SerialPort.getCommPort(comPortName);
            comPort.setBaudRate(baudRate);
            //If the port is not closed, open the USB port.
            if (comPort.isOpen() == false) {
                try {
                    comPort.openPort();
                    Thread.sleep(1000);
                    outPut = new PrintWriter(comPort.getOutputStream());
                } catch (Exception c) {
                }
            }
            if (comPort.isOpen() == true) {
                System.out.println("Connection to the sorting robot successful.");
                lblStatus.setText("Status: Connected");
                connected = true;
                topBar1.setConnected(true);
            } else {
                System.out.println("Error opening port.");
                lblStatus.setText("Status: Opening USB failed, please try to reconnect the USB");
                connected = false;
                topBar1.setConnected(false);
            }
        }

        if (e.getSource() == btnDisconnect && connected) {
            if (comPort.isOpen() == true) {
                comPort.closePort();
                System.out.println("Disconnected from Arduino.");
                lblStatus.setText("Status: Disconnected");
                connected = false;
                topBar1.setConnected(false);
            }
        }
        if (e.getSource() == btStart && connected) {
            if (comPort.isOpen() == true) {
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to start the sorting robot?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    System.out.println("Status: Running...");
                    lblStatus.setText("Status: Running...");
                    outPut.print("5-5-3\r\n");
                    outPut.flush();
                } else {
                    lblStatus.setText("Status: closed");
                }
            } else {
                System.out.println("Sorting robot isn't connected");
                lblStatus.setText("Sorting robot isn't connected");
            }
        }
        if (e.getSource() == btEStop && connected) {
            if (comPort.isOpen() == true) {
                System.out.println("Status: STOP");
                lblStatus.setText("Status: STOP");
                outPut.print("Stop\r\n");
                outPut.flush();
            } else {
                System.out.println("Sorting robot isn't connected");
                lblStatus.setText("Sorting robot isn't connected");
            }
        }
        repaint();
    }
}
