/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class Category {
    private String name;

    private Lookup lookup;

    public Category(String name, Lookup context) {
        this.setName(name);
        this.lookup = context;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lookup getLookup() {
        return this.lookup;
    }
}
