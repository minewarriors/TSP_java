package SerialController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PictureCheck extends JPanel{

    private boolean connected;

    private BufferedImage ConnectedPicture, DisconnectedPicture;

    public PictureCheck() {

        this.setPreferredSize(new Dimension(50, 50));
        try {
            ConnectedPicture = ImageIO.read(new File("C:\\Users\\Christiaan\\Desktop\\TSP_java\\TSP_KBS2a\\src\\SerialController\\ok.jpg"));
            DisconnectedPicture = ImageIO.read(new File("C:\\Users\\Christiaan\\Desktop\\TSP_java\\TSP_KBS2a\\src\\SerialController\\Fail.jpg"));
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
            g.drawImage(ConnectedPicture, 0, 0, 50, 50, null);
        } else {
            g.drawImage(DisconnectedPicture, 0, 0, 50, 50, null);
        }  
    }

    public boolean GetConnected() {
        return connected;
    }
    
}
