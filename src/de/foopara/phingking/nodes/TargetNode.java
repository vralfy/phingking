/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import de.foopara.phingking.nodes.actions.RunAction;
import de.foopara.phingking.registry.TargetEntry;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author n.specht
 */
public class TargetNode extends AbstractNode implements Comparable<TargetNode>{
    private TargetEntry target;

    public TargetNode(TargetEntry key) {
        super (Children.LEAF, Lookups.fixed(new Object[] {key}));
        this.target = key;
        this.setDisplayName(key.getTarget());
        this.setShortDescription(key.getDiscription());
        this.setIconBaseWithExtension("de/foopara/phingking/resources/target.png");
    }

    @Override
    public int compareTo(TargetNode node) {
        return this.target.getTarget().compareTo(node.getEntry().getTarget());
    }

    @Override
    public Action[] getActions(boolean popup) {
        RunAction run = SystemAction.get(RunAction.class);
        run.setTarget(this.target);
        return new Action [] {
            run
        };
    }

    public TargetEntry getEntry() {
        return this.target;
    }
}
