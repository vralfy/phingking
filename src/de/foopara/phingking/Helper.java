/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking;

import de.foopara.phingking.options.OptionMain;
import de.foopara.phingking.options.ProjectProperties;
import java.io.File;
import org.openide.util.Lookup;

public class Helper {
    public static boolean canRunPhing(Lookup lkp) {
        ProjectProperties pp = new ProjectProperties(lkp);

        String confPath = pp.get("buildfile", null);
        if (confPath == null) {
            return false;
        }
        File config = new File(confPath);
        File exe = new File(OptionMain.getExecutable());

        if (!exe.exists() || !exe.isFile() || !config.exists() || !config.isFile()) {
            return false;
        }

        return true;
    }
}
