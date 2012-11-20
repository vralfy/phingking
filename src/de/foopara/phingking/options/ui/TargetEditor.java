/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options.ui;

import de.foopara.phingking.registry.TargetEntry;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author n.specht
 */
public class TargetEditor {
    private TargetEntry entry;
    private JPanel main;
    private JTextField iDisplay;
    private JTextField iTarget;
    private JTextField iDiscr;
    private JTable iParams;

    public TargetEditor(TargetEntry entry) {
        this.entry = entry;
    }

    public void edit() {
        java.awt.GridBagConstraints gbc;

        this.main = new JPanel();
        this.main.setLayout(new java.awt.GridBagLayout());
// Display name
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        JLabel lDisplay = new JLabel("display name:");
        this.main.add(lDisplay, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        this.iDisplay = new JTextField(this.entry.getDisplayName());
        this.main.add(iDisplay, gbc);
// Target Chain
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        JLabel lTarget = new JLabel("target chain:");
        this.main.add(lTarget, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        this.iTarget = new JTextField(this.entry.getTarget());
        this.main.add(iTarget, gbc);
// Discription
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        JLabel lDiscr= new JLabel("discription:");
        this.main.add(lDiscr, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        this.iDiscr = new JTextField(this.entry.getDiscription());
        this.main.add(this.iDiscr, gbc);

//Parameter Header
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 2, 2, 2);
        JLabel lParams = new JLabel("parameters:");
        this.main.add(lParams, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(20, 2, 2, 2);
        JPanel actParams = new JPanel(new java.awt.GridBagLayout());
        this.main.add(actParams, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton addParams = new JButton("add");
        addParams.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addParamRow();
            }
        });
        actParams.add(addParams, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton delParams = new JButton("remove");
        delParams.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeParamRow();
            }
        });
        actParams.add(delParams, gbc);
// Parameter Table
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("name");
        model.addColumn("value");
        this.iParams = new JTable(model);
        AdvancedCellRenderer.setCellRenderer(this.iParams);
        TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);
        sorter.setModel(model);
        this.iParams.setRowSorter(sorter);
        this.iParams.setAutoCreateRowSorter(false);
        this.iParams.getColumnModel().getColumn(0).setMinWidth(150);
        this.iParams.getColumnModel().getColumn(0).setMaxWidth(150);
        this.iParams.setCellSelectionEnabled(true);
        this.iParams.setColumnSelectionAllowed(false);
        this.iParams.setRowSelectionAllowed(true);

        JScrollPane sParams = new JScrollPane(this.iParams);
        sParams.setMinimumSize(new Dimension(400, 300));
        sParams.setMaximumSize(new Dimension(400, 300));
        sParams.setPreferredSize(new Dimension(400, 300));
        this.main.add(sParams, gbc);
// Run Dialog
        for(String key : this.entry.getParameter().keySet()) {
            model.addRow(new Object[] {
                key,
                this.entry.getParameter().get(key)
            });
        }

        JOptionPane.showMessageDialog(null, main, "Edit target", JOptionPane.PLAIN_MESSAGE | JOptionPane.OK_OPTION);
//Save
        this.entry.setDisplayName(this.iDisplay.getText());
        this.entry.setTarget(this.iTarget.getText());
        this.entry.setDiscription(this.iDiscr.getText());
        this.entry.getParameter().clear();
        for (int i =0; i<model.getRowCount(); i++) {
            String key = (String)model.getValueAt(i, 0);
            String value = (String)model.getValueAt(i, 1);
            if (key.trim().length() > 0 && value.trim().length() > 0) {
                this.entry.getParameter().put(key, value);
            }
        };
    }

    public void addParamRow() {
        ((DefaultTableModel)this.iParams.getModel()).addRow(new Object[] {"", ""});
    }

    public void removeParamRow() {
        int row = this.iParams.getSelectedRow();
        if (row >= 0) {
            ((DefaultTableModel)this.iParams.getModel()).removeRow(row);
        }
    }
}
