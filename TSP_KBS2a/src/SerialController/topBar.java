package SerialController;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class topBar extends JPanel implements ActionListener{

    JButton btnConnect = new JButton("Connect");
    JButton btnDisconnect = new JButton("Disconnect");
    
    private boolean connected;

    private BufferedImage ConnectedPicture, DisconnectedPicture;

    public topBar() {
        btnConnect.setBackground(Color.green);
        btnDisconnect.setBackground(Color.red);
        btnDisconnect.addActionListener(this);
        btnConnect.addActionListener(this);
        this.setPreferredSize(new Dimension(1080, 80));
        try {
            ConnectedPicture = ImageIO.read(new File("C:\\xampp\\htdocs\\Serial\\src\\ok.jpg"));
            DisconnectedPicture = ImageIO.read(new File("C:\\xampp\\htdocs\\Serial\\src\\Fail.jpg"));
        } catch (IOException ex) {
            System.out.println("Plaatje niet gevonden.");
        }
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
         setBackground(Color.white);

        if (connected) {
            g.drawImage(ConnectedPicture, 25, 25, 50, 50, null);
           // add(btnDisconnect);
            //remove(btnConnect);
        } else {
            g.drawImage(DisconnectedPicture, 25, 25, 50, 50, null);
           // add(btnConnect);
           // remove(btnDisconnect);
        }

       
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == btnConnect) {
       connected = true;
           System.out.println("btnConnect");
       }
       if (e.getSource() == btnDisconnect) {
       connected = false;
       System.out.println("btnDisconnect");
       }
       repaint();
    }

    public boolean GetConnected() {
        return connected;
    }
    
}
