/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import de.foopara.phingking.Helper;
import de.foopara.phingking.options.OptionMain;
import de.foopara.phingking.options.ProjectProperties;
import de.foopara.phingking.registry.TargetEntry;
import de.foopara.phingking.registry.TargetRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class RunTarget {
    private final Lookup lookup;
    private final TargetEntry target;

    public RunTarget(Lookup context, TargetEntry target) {
        this.lookup = context;
        this.target = target;
    }

    public void run() {
        try {
            if (!Helper.canRunPhing(this.lookup)) {
                return;
            }

            ProjectProperties pp = new ProjectProperties(this.lookup);
            StringBuilder cmd = new StringBuilder();

            File config = new File(pp.get("buildfile", null));
            File exe = new File(OptionMain.getExecutable());
            File logfile = File.createTempFile("phingking", ".log");

            cmd.append(exe.getAbsolutePath())
                    .append(" -q -logfile ")
                    .append(logfile.getAbsolutePath())
                    .append(" -f ")
                    .append(config.getAbsolutePath())
                    .append(" ")
                    .append(this.target.getTarget());

            Process child = Runtime.getRuntime().exec(cmd.toString(), null, config.getParentFile());
            StringBuilder tmp = new StringBuilder();
            InputStream in = child.getInputStream();
            int c;

            UserInput input = null;

            while((c = in.read()) != -1) {
                    tmp.append((char)c);
                    System.out.print(tmp);
                    if (tmp.toString().trim().length() > 0) {
                        if (input == null) {
                            input = new UserInput(child.getOutputStream());
                            input.setText(tmp.toString().trim());
                            input.start();
                        } else {
                            input.appendText(tmp.toString());
                        }
                        tmp = new StringBuilder();
                    }
            }
//            JOptionPane.showMessageDialog(null, tmp.toString());

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
