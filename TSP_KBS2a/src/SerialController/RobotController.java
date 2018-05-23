package SerialController;

import com.fazecast.jSerialComm.SerialPort;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class RobotController extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private SerialPort comPort;
    private String comPortName;
    private final int baudRate = 9600;
    static PrintWriter outPut;
    private boolean connected = false;
    DataLogger DataLogger1 = new DataLogger();

    public RobotController() {
        initComponents();
        setVisible(true);
    }

    public void updateMonitor() {

        jTextAreaMonitor.selectAll();
        jTextAreaMonitor.replaceSelection("");

        for (String s : DataLogger1.getData()) {
            jTextAreaMonitor.append(s + "\n"); // New line at the end
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        pictureCheck1 = new SerialController.PictureCheck();
        btStart = new javax.swing.JButton();
        btEStop = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMonitor = new javax.swing.JTextArea();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btStart.setText("Start");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btEStop.setText("Stop");
        btEStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEStopActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COM1", "COM2", "COM3", "COM4" , "COM5", "COM6", "COM7" , "COM8", "COM9", "COM10"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextAreaMonitor.setColumns(20);
        jTextAreaMonitor.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMonitor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btEStop, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pictureCheck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnConnect)
                                    .addComponent(jComboBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDisconnect)
                                .addGap(107, 107, 107)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pictureCheck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEStop, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        if (connected) {
            if (comPort.isOpen() == true) {
                comPort.closePort();
                DataLogger1.addData("Disconnected from sorting robot.");
                connected = false;
                pictureCheck1.setConnected(false);
            }
        }
        updateMonitor();
        repaint();
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        if (!connected) {
            comPort = SerialPort.getCommPort(comPortName);
            comPort.setBaudRate(baudRate);
            DataLogger1.addData("Trying to connect...");
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
                DataLogger1.addData("Connection to the sorting robot is successful.");
                connected = true;
                pictureCheck1.setConnected(true);
            } else {
                DataLogger1.addData("Error opening port.");
                connected = false;
                pictureCheck1.setConnected(false);
            }

        } else {
            DataLogger1.addData("The robot is already connected.");
        }
        updateMonitor();
        repaint();
    }//GEN-LAST:event_btnConnectActionPerformed

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
        DataLogger1.addData("Selected : " + comPortName);
        updateMonitor();
        repaint();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        if (connected) {
            if (comPort.isOpen() == true) {
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to start the sorting robot?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    DataLogger1.addData("Status: Running...");
                    outPut.print("5-5-3\r\n");
                    outPut.flush();
                    DataLogger1.addData("Command send to robot: " + "5-5-3\r\n");
                } else {
                }
            } else {
                DataLogger1.addData("Sorting robot isn't connected");
            }
            updateMonitor();
        }
    }//GEN-LAST:event_btStartActionPerformed

    private void btEStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEStopActionPerformed
        if (connected) {
            if (comPort.isOpen() == true) {
                DataLogger1.addData("Status: STOP");
                outPut.print("Stop\r\n");
                outPut.flush();
                DataLogger1.addData("Command send to robot: " + "Stop\r\n");
            } else {
                DataLogger1.addData("Sorting robot isn't connected");
            }
            updateMonitor();
        }
    }//GEN-LAST:event_btEStopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEStop;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaMonitor;
    private SerialController.PictureCheck pictureCheck1;
    // End of variables declaration//GEN-END:variables

}
