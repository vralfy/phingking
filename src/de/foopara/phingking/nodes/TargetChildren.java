/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import java.util.ArrayList;
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
        childrenNodes.add(new TargetNode(new Target("1")));
        childrenNodes.add(new TargetNode(new Target("2")));
        childrenNodes.add(new TargetNode(new Target("3")));
        childrenNodes.add(new TargetNode(new Target("4")));
        childrenNodes.add(new TargetNode(new Target("RubbelDieKatz")));
        return childrenNodes;
    }
}
