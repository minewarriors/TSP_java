/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Bram ten Brinke
 */
public class RandomSearchDialog extends JDialog implements ActionListener {
    private JLabel jlNumber, jlWarning;
    private JButton  jbConfirm, jbCancel;
    private JTextArea jTextArea;
    
    public RandomSearchDialog() {
        setTitle("Willekeurig zoeken instellen");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        
        jlNumber = new JLabel("Aantal simulaties:");
        jbConfirm = new JButton("Start Simulatie");
        jbCancel = new JButton("Annuleren");
        jTextArea = new JTextArea();
        jlWarning = new JLabel();
        
        this.add(jlNumber);
        this.add(jTextArea);
        this.add(jbCancel);
        this.add(jbConfirm);
        this.add(jlWarning);
        
        jbConfirm.addActionListener(this);
        jbCancel.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            dispose();
        }
        if (e.getSource() == jbConfirm) {
            jlWarning.setText("Vul ");
            jlWarning.setForeground(Color.RED);
            
        }
        
    }
    
}
