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
public class CategoryNode extends AbstractNode {

    public CategoryNode(Category category) {
        super(
                new TargetChildren(category),
                Lookups.singleton(category)
        );
        this.setDisplayName(category.getName());
        this.setIconBaseWithExtension("de/foopara/phingking/resources/category.png");
    }

    @Override
    public Cookie getCookie(Class clazz) {
        Children ch = getChildren();

        if (clazz.isInstance(ch)) {
            return (Cookie) ch;
        }

        return super.getCookie(clazz);
    }
}
