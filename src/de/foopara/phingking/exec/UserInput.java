/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author nspecht
 */
public class UserInput extends Thread {
    private final OutputStream out;
    private final InputHandler ih;

    private final JPanel mainPanel = new JPanel();
    private final JLabel errorLabel = new JLabel();
    private final JScrollPane infoScroll = new JScrollPane();
    private final JLabel infoLabel = new JLabel();
    private final JTextField inputField = new JTextField();
    private StringBuilder promptBuffer = new StringBuilder();

    private boolean done = false;

    public UserInput(OutputStream os, InputHandler ih) {
        this.out = os;
        this.ih = ih;
        this.initComponents();
    }

    public void setText(String txt) {
        this.promptBuffer = new StringBuilder(txt);
        this.updateText();
    }

    public void appendText(String txt) {
        this.promptBuffer.append(txt);
        this.updateText();
    }

    private void updateText() {
        String txt = this.promptBuffer.toString();
        System.out.println(txt);
        if (this.ih != null && txt.contains(ih.getInputMarker()) && txt.lastIndexOf(ih.getInputMarker()) > 0) {
            try {
                String log = txt.substring(0, txt.lastIndexOf(ih.getInputMarker()));
                this.out.write(log.getBytes());
                txt = txt.substring(txt.lastIndexOf(ih.getInputMarker()));
                this.promptBuffer = new StringBuilder(txt);
            } catch (IOException ex) {
            }
        }

        if (this.ih != null) {
            txt = txt.replace(ih.getInputMarker(), "");
        }
        txt = txt.replaceAll("\n", "<br>");
        txt = txt.replaceAll(" ", "&nbsp;");

        this.infoLabel.setText("<html><body>" + txt + "</body></html>");

        int width = Math.max(this.infoLabel.getSize().width, 600);
        int height = Math.max(100, Math.min(this.infoLabel.getSize().height, 600));
        this.infoScroll.setMinimumSize(new Dimension(10, 100));
        this.infoScroll.setMaximumSize(new Dimension(600, 600));
        this.infoScroll.setPreferredSize(new Dimension(width, height));
        JScrollBar vertical = this.infoScroll.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        this.infoScroll.repaint();
        this.mainPanel.repaint();

        this.startSave();
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
        this.infoLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
        this.infoScroll.setViewportView(this.infoLabel);

        this.mainPanel.add(infoScroll, gbc);

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
        this.infoScroll.setVisible(false);
    }

    public void setDone() {
        this.errorLabel.setText("<html><body><b>Phing has terminated</b></body></html>");
        this.setErrorMode();
    }

    public void startSave()
    {
        if (this.isAlive()) {
            return;
        }

        if (this.isDone()) {
            return;
        }

        boolean start = false;
        if (this.ih == null) {
            start = true;
        } else if (this.promptBuffer.toString().contains(this.ih.getInputMarker())) {
            start = true;
        }

        if (start) {
            this.start();
        }
    }
}
