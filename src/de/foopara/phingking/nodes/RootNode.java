package de.foopara.phingking.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author n.specht
 */
public class RootNode extends AbstractNode {
    public RootNode(Children children)
    {
        super(children);
        this.setDisplayName("Targets");
        this.setIconBaseWithExtension("de/foopara/phingking/resources/category.png");
    }
}
