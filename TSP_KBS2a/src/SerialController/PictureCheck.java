package SerialController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PictureCheck extends JPanel {

    private int connected = 2;

    private BufferedImage ConnectedPicture, DisconnectedPicture;

    public PictureCheck() {

        this.setPreferredSize(new Dimension(50, 50));
        try {
            ConnectedPicture = ImageIO.read(getClass().getResource("ok.png"));
            DisconnectedPicture = ImageIO.read(getClass().getResource("fail.png"));
        } catch (IOException ex) {
            System.out.println("Plaatje niet gevonden.");
        }
    }

    public void setConnected(int connected) {
        this.connected = connected;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setBackground(Color.white);

        if (connected == 1) {
            g.drawImage(ConnectedPicture, 0, 0, 50, 50, null);
        } else if (connected == 2) {
            g.drawImage(DisconnectedPicture, 0, 0, 50, 50, null);
        } else if (connected == 3) {
            g.drawImage(DisconnectedPicture, 0, 0, 50, 50, null);
        }
    }

    public boolean GetConnected() {
        if (connected == 1) {
            return true;
        } else {
            return false;
        }
    }

}
