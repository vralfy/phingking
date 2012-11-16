/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes.actions;

import de.foopara.phingking.NavigatorTopComponent;
import de.foopara.phingking.registry.TargetEntry;
import java.awt.event.ActionEvent;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallbackSystemAction;

/**
 *
 * @author nspecht
 */
public class RunAction extends CallbackSystemAction {

    private TargetEntry target;

    public void setTarget(TargetEntry target) {
        this.target = target;
    }

    @Override
    public void initialize() {
        super.initialize();
        this.setEnabled(true);
        this.setSurviveFocusChange(true);
    }

    @Override
    public String getName() {
        return "Run " + this.target.getTarget().trim();
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
        System.out.println("Try to run " + this.target.getTarget() + "...\n\n\n");
        NavigatorTopComponent.getInstance().runTarget(this.target);
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
