package SerialController;

import Core.Box;
import Core.Product;
import static SerialController.CommandGenenrator.generateCommandArray;
import com.fazecast.jSerialComm.SerialPort;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Christiaan
 */
public class RobotControllerJpanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private SerialPort comPort;
    private String comPortName;
    private final int baudRate = 9600;
    static PrintWriter outPut;
    private boolean connected = false;
    private Box doosA;
    private Box doosB;
    private Box doosC;
    ArrayList<Product> TSProute = new ArrayList<>();
    ArrayList<String> commands = new ArrayList<>();

    public RobotControllerJpanel(ArrayList<Product> TSProute, Box a, Box b, Box c) {
        initComponents();
        this.TSProute = TSProute;
        doosA = a;
        doosB = b;
        doosC = c;
    }

    public void updateMonitor() {

        jTextAreaMonitor1.selectAll();
        jTextAreaMonitor1.replaceSelection("");

        for (String s : DataLogger.getData()) {
            jTextAreaMonitor1.append(s + "\n"); // New line at the end
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMonitor = new javax.swing.JTextArea();
        pictureCheck1 = new SerialController.PictureCheck();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMonitor1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        btStart = new javax.swing.JButton();
        btEStop = new javax.swing.JButton();

        jTextAreaMonitor.setColumns(20);
        jTextAreaMonitor.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMonitor);

        javax.swing.GroupLayout pictureCheck1Layout = new javax.swing.GroupLayout(pictureCheck1);
        pictureCheck1.setLayout(pictureCheck1Layout);
        pictureCheck1Layout.setHorizontalGroup(
            pictureCheck1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pictureCheck1Layout.setVerticalGroup(
            pictureCheck1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jTextAreaMonitor1.setColumns(20);
        jTextAreaMonitor1.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMonitor1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COM1", "COM2", "COM3", "COM4" , "COM5", "COM6", "COM7" , "COM8", "COM9", "COM10"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btnConnect.setBackground(new java.awt.Color(51, 255, 0));
        btnConnect.setText("connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnDisconnect.setBackground(new java.awt.Color(255, 51, 51));
        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        btStart.setText("Start");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btEStop.setText("Recalibrate");
        btEStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(pictureCheck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btEStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btStart, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureCheck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConnect))
                                .addGap(18, 18, 18)
                                .addComponent(btnDisconnect)))
                        .addGap(22, 22, 22)
                        .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btEStop, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String com = (String) jComboBox1.getSelectedItem();
        if ("COM1".equals(com)) {
            comPortName = "COM1";
        }
        if ("COM2".equals(com)) {
            comPortName = "COM2";
        }
        if ("COM3".equals(com)) {
            comPortName = "COM3";
        }
        if ("COM4".equals(com)) {
            comPortName = "COM4";
        }
        if ("COM5".equals(com)) {
            comPortName = "COM5";
        }
        if ("COM6".equals(com)) {
            comPortName = "COM6";
        }
        if ("COM7".equals(com)) {
            comPortName = "COM7";
        }
        if ("COM8".equals(com)) {
            comPortName = "COM8";
        }
        if ("COM9".equals(com)) {
            comPortName = "COM9";
        }
        if ("COM10".equals(com)) {
            comPortName = "COM10";
        }
        System.out.println(comPortName);
        DataLogger.addData("Selected : " + comPortName);
        updateMonitor();
        repaint();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        if (!connected) {
            comPort = SerialPort.getCommPort(comPortName);
            comPort.setBaudRate(baudRate);
            DataLogger.addData("Trying to connect...");
            updateMonitor();
            repaint();
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
                DataLogger.addData("Connection to the sorting robot is successful.");
                connected = true;
                pictureCheck1.setConnected(1);
            } else {
                DataLogger.addData("Error opening port.");
                connected = false;
                pictureCheck1.setConnected(2);
            }

        } else {
            DataLogger.addData("The robot is already connected.");
        }
        updateMonitor();
        repaint();
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        if (connected) {
            if (comPort.isOpen() == true) {
                comPort.closePort();
                DataLogger.addData("Disconnected from sorting robot.");
                connected = false;
                pictureCheck1.setConnected(2);
            }
        } else {
            DataLogger.addData("The sorting robot is aready disconnected.");
        }
        updateMonitor();
        repaint();
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        if (connected) {
            commands = generateCommandArray(TSProute, doosA, doosB, doosC);
            if (comPort.isOpen() == true) {
                if (commands.size() > 0) {
                    int selectedOption = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to start the sorting robot?",
                            "Choose",
                            JOptionPane.YES_NO_OPTION);
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        DataLogger.addData("Status: Running...");
                        for (String commandString : commands) {
                            outPut.print(commandString);
                            outPut.flush();
                            DataLogger.addData("Command send to robot: " + commandString);
                        }
                    }
                } else {
                    DataLogger.addData("please calculate first");
                }
            }
        } else {
            DataLogger.addData("Sorting robot isn't connected");
        }
        updateMonitor();
        repaint();
    }//GEN-LAST:event_btStartActionPerformed

    private void btEStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEStopActionPerformed
        if (connected) {
            if (comPort.isOpen() == true) {
                DataLogger.addData("Status: STOP");
                 outPut.print("1.1.1.9\r\n");
                 outPut.flush();
                DataLogger.addData("Command send to robot: " + "Stop\r\n");
            }
        } else {
            DataLogger.addData("Sorting robot isn't connected");
        }
        updateMonitor();
        repaint();
    }//GEN-LAST:event_btEStopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEStop;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaMonitor;
    private javax.swing.JTextArea jTextAreaMonitor1;
    private SerialController.PictureCheck pictureCheck1;
    // End of variables declaration//GEN-END:variables
}
