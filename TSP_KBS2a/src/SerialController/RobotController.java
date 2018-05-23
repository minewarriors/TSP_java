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

    public RobotController() {
        initComponents();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jTextField1 = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        pictureCheck1 = new SerialController.PictureCheck();
        btStart = new javax.swing.JButton();
        btEStop = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("jTextField1");

        btnConnect.setText("connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pictureCheck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btStart, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btEStop, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1))
                                .addGap(18, 18, 18)
                                .addComponent(btnDisconnect))
                            .addComponent(pictureCheck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btStart, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btEStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        if (connected) {
            if (comPort.isOpen() == true) {
                comPort.closePort();
                System.out.println("Disconnected from Arduino.");
                connected = false;
                pictureCheck1.setConnected(false);
            }
        }
        repaint();
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

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
            connected = true;
            pictureCheck1.setConnected(true);
        } else {
            System.out.println("Error opening port.");
            connected = false;
            pictureCheck1.setConnected(false);
        }
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
                    System.out.println("Status: Running...");
                    outPut.print("5-5-3\r\n");
                    outPut.flush();
                } else {
                }
            } else {
                System.out.println("Sorting robot isn't connected");
            }
        }
    }//GEN-LAST:event_btStartActionPerformed

    private void btEStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEStopActionPerformed
       if (connected) {
            if (comPort.isOpen() == true) {
                System.out.println("Status: STOP");
                outPut.print("Stop\r\n");
                outPut.flush();
            } else {
                System.out.println("Sorting robot isn't connected");
            }
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
    private javax.swing.JTextField jTextField1;
    private SerialController.PictureCheck pictureCheck1;
    // End of variables declaration//GEN-END:variables

}
