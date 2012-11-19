/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options.ui;

import de.foopara.phingking.registry.TargetEntry;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author n.specht
 */
public class TargetEditor {
    private TargetEntry entry;
    private JPanel main;
    private JLabel lDisplay;
    private JTextField iDisplay;
    private JLabel lTarget;
    private JTextField iTarget;
    private JTable iParams;

    public TargetEditor(TargetEntry entry) {
        this.entry = entry;
    }

    public void edit() {
        java.awt.GridBagConstraints gbc;

        this.main = new JPanel();
        this.main.setLayout(new java.awt.GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        this.lDisplay = new JLabel("display name:");
        this.main.add(lDisplay, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.iDisplay = new JTextField(this.entry.getDisplayName());
        this.main.add(iDisplay, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        this.lTarget = new JLabel("target chain:");
        this.main.add(lTarget, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.iTarget = new JTextField(this.entry.getTarget());
        this.main.add(iTarget, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("name");
        model.addColumn("value");
        this.iParams = new JTable(model);
        AdvancedCellRenderer.setCellRenderer(this.iParams);
        TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);
        sorter.setModel(model);
        this.iParams.setRowSorter(sorter);
        this.iParams.setAutoCreateRowSorter(false);
//        this.iParams.setRowSorter(new TableRowSorter<DefaultTableModel>());
        this.iParams.getColumnModel().getColumn(0).setMinWidth(150);
        this.iParams.getColumnModel().getColumn(0).setMaxWidth(150);
        this.iParams.setMinimumSize(new Dimension(400, 300));
        this.iParams.setMaximumSize(new Dimension(400, 300));
        this.iParams.setPreferredSize(new Dimension(400, 300));
        this.main.add(iParams, gbc);
        model.addRow(new Object[] {"value1", "value2"});


        JOptionPane.showMessageDialog(null, main, "Edit target", JOptionPane.PLAIN_MESSAGE | JOptionPane.OK_OPTION);

        this.entry.setDisplayName(iDisplay.getText());
        this.entry.setTarget(iTarget.getText());
    }
}
