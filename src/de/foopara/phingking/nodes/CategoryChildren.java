/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes;

import de.foopara.phingking.registry.TargetRegistry;
import java.util.Set;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class CategoryChildren extends Children.Keys {
    private final Lookup lookup;

    public CategoryChildren(Lookup context) {
        this.lookup = context;
    }

    @Override
    protected Node[] createNodes(Object key) {
        Category obj = (Category) key;
        return new Node[] {new CategoryNode( obj )};
    }

    @Override
    protected void addNotify() {
        super.addNotify();
        Set<String> categoriesSet = TargetRegistry.getInstance(this.lookup).getCategories();
        Category[] categories = new Category[categoriesSet.size()];
        int i = 0;
        for (String cat : categoriesSet) {
            categories[i] = new Category(cat);
            i++;
        }
        setKeys(categories);
    }
}
