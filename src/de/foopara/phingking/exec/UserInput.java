/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import java.awt.GridBagConstraints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openide.util.Exceptions;

/**
 *
 * @author nspecht
 */
public class UserInput extends Thread {
    private final OutputStream out;
    private final JPanel mainPanel = new JPanel();
    private final JLabel errorLabel = new JLabel();
    private final JLabel infoLabel = new JLabel();
    private final JTextField inputField = new JTextField();
    private boolean done = false;

    public UserInput(OutputStream os) {
        this.out = os;
        this.initComponents();
    }

    public void setText(String txt) {
        this.infoLabel.setText(txt);
    }

    public void appendText(String txt) {
        this.infoLabel.setText(this.infoLabel.getText() + txt);
    }

    @Override
    public void run() {
        try {
            //        if (!SwingUtilities.isEventDispatchThread())
            //            throw new IllegalStateException("You should not be calling this logic outside the Swing Event Thread!");
            //        JOptionPane jop = new JOptionPane(this.mainPanel, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION);
            //        JDialog jd = jop.createDialog(null, "User Input");
            //        jd.setVisible(true);
            //        jd.setVisible(true);

            JOptionPane.showMessageDialog(null, this.mainPanel, "User input", JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
            if (this.out != null) {
                this.out.write((this.inputField.getText() + "\n").getBytes());
                this.out.flush();
            }
            this.done = true;
        } catch (IOException ex) {
        }
    }

    private void initComponents() {
        this.mainPanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.errorLabel.setText("<html><body><b>An error occurred, verify in Output window.</b></body></html>");
        this.errorLabel.setVisible(false);
        this.mainPanel.add(this.errorLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.mainPanel.add(this.infoLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.inputField.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent ce) {
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                ce.getComponent().requestFocus();
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
            }
        });
        this.mainPanel.add(this.inputField, gbc);
    }

    public boolean isDone() {
        return this.done;
    }

    public void setErrorMode() {
        this.errorLabel.setVisible(true);
        this.inputField.setVisible(false);
        this.infoLabel.setVisible(false);
    }
}
