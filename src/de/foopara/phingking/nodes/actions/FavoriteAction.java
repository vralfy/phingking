/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.nodes.actions;

import de.foopara.phingking.NavigatorTopComponent;
import de.foopara.phingking.options.FavoritedTargets;
import de.foopara.phingking.options.ui.TargetEditor;
import de.foopara.phingking.registry.TargetEntry;
import de.foopara.phingking.registry.TargetList;
import de.foopara.phingking.registry.TargetRegistry;
import java.awt.event.ActionEvent;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallbackSystemAction;

/**
 *
 * @author nspecht
 */
public class FavoriteAction extends CallbackSystemAction {

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
        return "Add to favorites";
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
        TargetRegistry tr = TargetRegistry.getInstance(NavigatorTopComponent.getInstance().getCurrentLookup());
        TargetList tl = tr.getTargets("Favorites:");

        TargetEntry newEntry = new TargetEntry(this.target.getTarget());
        newEntry.isFavorite(true);
        TargetEditor te = new TargetEditor(newEntry);
        te.edit();
        tl.add(newEntry);

        FavoritedTargets.put(tl, NavigatorTopComponent.getInstance().getCurrentLookup());
        NavigatorTopComponent.getInstance().update();
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
