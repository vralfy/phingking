/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import de.foopara.phingking.registry.TargetEntry;

/**
 *
 * @author n.specht
 */
public class Target {
    private TargetEntry target;

    public Target(TargetEntry t) {
        this.target = t;
    }

    public String getName() {
        return this.target.getTarget();
    }
}
