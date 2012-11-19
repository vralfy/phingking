/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.registry;

import de.foopara.phingking.exec.ListTargets;
import de.foopara.phingking.exec.RunTarget;
import de.foopara.phingking.options.FavoritedTargets;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
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
        Project pro = lookup.lookup(Project.class);
        if (pro == null) {
            pro = FileOwnerQuery.getOwner(primary);
        }
        if (pro != null) {
            primary = pro.getProjectDirectory();
        }

        if (!TargetRegistry.instances.containsKey(primary.getPath())) {
            TargetRegistry item = new TargetRegistry();
            TargetRegistry.instances.put(primary.getPath(), item);
            item.update(lookup);
        }
        return TargetRegistry.instances.get(primary.getPath());
    }

    private HashMap<String, TargetList> categories = new HashMap<String, TargetList>();
    private UUID viewHash = null;

    public TargetRegistry() {
        this.viewHash = UUID.randomUUID();
    }

    public void update(Lookup lkp) {
        this.categories.clear();
        this.categories.put("Favorites:", FavoritedTargets.get(lkp));
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
                    targets.add(this.parseToTargetEntry(line));
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

    private TargetEntry parseToTargetEntry(String line) {
        String target = line.trim();
        String discr = "";
        if (target.indexOf(" ") > 0) {
            discr = target.substring(target.indexOf(" ")).trim();
            target = target.substring(0, target.indexOf(" "));
        }

        TargetEntry t = new TargetEntry(target);
        t.setDiscription(discr);

        return t;
    }

    public UUID getId() {
        return this.viewHash;
    }
}
