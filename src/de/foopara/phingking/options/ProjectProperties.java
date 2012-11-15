/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class ProjectProperties {
    public Lookup lookup = null;

    public ProjectProperties(Lookup lkp) {
        this.lookup = lkp;
    }

    public void put(String name, String value) {
        try {
            File config = this.getConfigFile();
            if (config != null) {
                Properties p = new Properties();
                p.load(new FileInputStream(config));
                p.setProperty(name, value);
                p.store(new FileOutputStream(config), "Phingking options");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public String get(String name, String def) {
        File config = this.getConfigFile();
        if (config == null) {
            System.err.println("config not found");
            return def;
        }

        Properties p = new Properties();
        try {
            p.load(new FileInputStream(config));
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return p.getProperty(name, def);
    }

    private File getConfigFile() {
        Project project = this.getProjectFromLookup();
        if (project == null) {
            return null;
        }
        FileObject fo = project.getProjectDirectory();

        File config = new File(FileUtil.toFile(fo), "nbproject/phingking.properties");
        if (!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return config;
    }

    protected Project getProjectFromLookup() {
        Project ret = this.lookup.lookup(Project.class);
        //Try getting it from Dataobject
        if (ret == null) {
            DataObject dataObject = this.lookup.lookup(DataObject.class);
            if (dataObject != null) {
                FileObject primary = dataObject.getPrimaryFile();
                ret = FileOwnerQuery.getOwner(primary);
            }
        }
        return ret;
    }
}
