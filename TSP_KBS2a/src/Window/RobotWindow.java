package Window;

import static Core.BPPInterface.boxSize;
import Core.Order;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Bart
 */
public class RobotWindow extends JFrame implements ActionListener {

    JComboBox algorithmList = new JComboBox();
    Order order = new Order();
    Core.Box A = new Core.Box(boxSize);
    Core.Box B = new Core.Box(boxSize);
    Core.Box C = new Core.Box(boxSize);
    BPPDrawPanel bppDP = new BPPDrawPanel(A, B, C);
    DrawPanel tspDP = new DrawPanel();

    public RobotWindow() {
        setTitle("Robot Controller");
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setSize(1920, 1080);
        setLayout(new BorderLayout());
        setResizable(false);

        add(tspDP, BorderLayout.WEST);
        add(bppDP, BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
