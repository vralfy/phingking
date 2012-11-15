/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author n.specht
 */
public class PropertiesOKAction  implements ActionListener {

    PropertiesPanel panel = null;

    public void setPanel(PropertiesPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.panel.save();
    }

}
