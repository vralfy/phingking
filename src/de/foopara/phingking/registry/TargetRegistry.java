/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.registry;

import de.foopara.phingking.exec.ListTargets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class TargetRegistry {
    private static HashMap<String, TargetRegistry> instances = new HashMap<String, TargetRegistry>();

    public static TargetRegistry getInstance(Lookup lookup) {
        DataObject dataObject = lookup.lookup(DataObject.class);
        if (dataObject == null) {
            return null;
        }
        FileObject primary = dataObject.getPrimaryFile();
        if (!TargetRegistry.instances.containsKey(primary.getPath())) {
            TargetRegistry item = new TargetRegistry();
            TargetRegistry.instances.put(primary.getPath(), item);
            item.update(lookup);
        }
        return TargetRegistry.instances.get(primary.getPath());
    }

    public class TargetList extends HashSet<String> {};
    private HashMap<String, TargetList> categories = new HashMap<String, TargetList>();

    public void update(Lookup lkp) {
        this.categories.clear();
        ListTargets lt = new ListTargets(lkp);
        lt.run();
    }

    public void parse(String str) {
        String[] split = str.split("\n\n");
        for(String cat : split) {
            String[] catSplit = cat.split("\n");
            String catTitle = "";
            boolean foundCatTitle = false;
            TargetList targets = new TargetList();
            for(String line : catSplit) {
                if (foundCatTitle) {
                    targets.add(line.trim());
                }
                if (line.trim().startsWith("------------")) {
                    foundCatTitle = true;
                }
                if (!foundCatTitle) {
                    catTitle = line.trim();
                }
            }
            this.categories.put(catTitle.trim(), targets);
        }
    }

    public Set<String> getCategories() {
        return this.categories.keySet();
    }

    public TargetList getTargets(String categorie) {
        return this.categories.get(categorie);
    }
}
