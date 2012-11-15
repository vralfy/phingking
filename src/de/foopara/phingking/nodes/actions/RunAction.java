/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes.actions;

import java.awt.event.ActionEvent;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallbackSystemAction;

/**
 *
 * @author nspecht
 */
public class RunAction extends CallbackSystemAction {

    @Override
    public void initialize() {
        super.initialize();
        this.setEnabled(true);
        this.setSurviveFocusChange(true);
    }

    @Override
    public String getName() {
        return "Run";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

    @Override
    public String iconResource() {
        return "de/foopara/phingking/resources/icon.png";
    }

    @Override
    public boolean asynchronous() {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        System.out.println("BLABLABLA\n\n\n");
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    @Override
//    public Object getActionMapKey() {
//        return new RunPerformer();
//    }

}
