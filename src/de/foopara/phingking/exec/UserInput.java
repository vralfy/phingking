/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import java.awt.GridBagConstraints;
import java.io.OutputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nspecht
 */
public class UserInput extends Thread {
    private final OutputStream out;
    private final JLabel infoLabel;
//    JScrollPane scroll = new JScrollPane();
//    JTextArea area = new JTextArea();
//    scroll.setViewportView(area);
//    scroll.setPreferredSize(new Dimension(500,300));
//    scroll.setMaximumSize(new Dimension(500,300));
//
//    area.setText(result);
//

    public UserInput(OutputStream os) {
        this.out = os;
        this.infoLabel = new JLabel();
    }

    public void setText(String txt) {
        this.infoLabel.setText(txt);
    }

    public void appendText(String txt) {
        this.infoLabel.setText(this.infoLabel.getText() + txt);
    }

    @Override
    public void run() {
        JPanel main = new JPanel();
        main.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        main.add(this.infoLabel, gbc);
        JTextField field = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        main.add(field,gbc);

        JOptionPane.showMessageDialog(null, main, "User input", JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);

    }

}
