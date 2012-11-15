/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import de.foopara.phingking.registry.TargetRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author n.specht
 */
public class TargetChildren extends Index.ArrayChildren {
    private Category category;

    public TargetChildren(Category category) {
        this.category = category;
    }

    @Override
    protected List<Node> initCollection() {
        ArrayList childrenNodes = new ArrayList();
        TargetRegistry tr = TargetRegistry.getInstance(this.category.getLookup());
        for (String target : tr.getTargets(this.category.getName())) {
            childrenNodes.add(new TargetNode(new Target(target)));
        }
        Collections.sort(childrenNodes);
        return childrenNodes;
    }
}
