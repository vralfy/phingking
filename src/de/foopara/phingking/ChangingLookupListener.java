/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking;

import java.util.Collection;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author nspecht
 */
public class ChangingLookupListener implements LookupListener {

    @Override
    public void resultChanged(LookupEvent ev) {
        Collection c = ((Lookup.Result) ev.getSource()).allInstances();
        for (Object i : c) {
            DataObject item = (DataObject)i;
            item.getPrimaryFile();
            if (NavigatorTopComponent.getInstance() != null) {
                NavigatorTopComponent.getInstance().setLookup(item.getLookup());
            }
        }
    }

}
