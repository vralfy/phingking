/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import de.foopara.phingking.Helper;
import de.foopara.phingking.options.OptionMain;
import de.foopara.phingking.options.ProjectProperties;
import de.foopara.phingking.registry.TargetRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class ListTargets {
    private final Lookup lookup;

    public ListTargets(Lookup context) {
        this.lookup = context;
    }

    public void run() {
        try {
            ProjectProperties pp = new ProjectProperties(this.lookup);
            StringBuilder cmd = new StringBuilder();

            if (!Helper.canRunPhing(this.lookup)) {
                return;
            }

            File config = new File(pp.get("buildfile", null));
            File exe = new File(OptionMain.getExecutable());

            cmd.append(exe.getAbsolutePath())
                    .append(" -f ")
                    .append(config.getAbsolutePath())
                    .append(" -l");
            Process child = Runtime.getRuntime().exec(cmd.toString(), null, config.getParentFile());
            StringBuilder tmp = new StringBuilder();
            InputStream in = child.getInputStream();

            int c;
            while((c = in.read()) != -1) {
                    tmp.append((char)c);
            }

            TargetRegistry.getInstance(this.lookup).parse(tmp.toString());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
