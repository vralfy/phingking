/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author n.specht
 */
public class TargetNode extends AbstractNode {
    private Target target;

    public TargetNode(Target key) {
        super (Children.LEAF, Lookups.fixed(new Object[] {key}));
        this.target = key;
        this.setDisplayName(key.getName());
        this.setIconBaseWithExtension("de/foopara/phingking/resources/target.png");
    }
}
