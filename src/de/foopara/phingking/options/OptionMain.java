/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options;

import de.foopara.phingking.NavigatorTopComponent;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author n.specht
 */
public class OptionMain {

    public static String PREFIX = "de.foopara.phingking.";

    protected static Preferences _modul() {
        return NbPreferences.forModule(NavigatorTopComponent.class);
    }

    public static String getExecutable() {
        return OptionMain._modul().get(OptionMain.PREFIX + "executable", "/usr/bin/phing");
    }
    public static void setExecutable(String exe) {
        OptionMain._modul().put(OptionMain.PREFIX + "executable", exe);
    }
}
