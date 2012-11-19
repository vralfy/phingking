/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options;

import de.foopara.phingking.registry.TargetEntry;
import de.foopara.phingking.registry.TargetList;
import java.io.*;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author n.specht
 */
public class FavoritedTargets {
    public static TargetList get(Lookup lkp) {
        ObjectInputStream ois = null;
        try {
            ProjectProperties props = new ProjectProperties(lkp);
            String strObj = props.get("favorite.targets", null);
            if (strObj == null || strObj.trim().length() < 1) {
                return new TargetList();
            }
            byte[] buffer = new BASE64Decoder().decodeBuffer(props.get("favorite.targets", null));
            ois = new ObjectInputStream(new ByteArrayInputStream(buffer));
            TargetList list = (TargetList)ois.readObject();
            for(TargetEntry entry : list) {
                entry.isFavorite(true);
            }
            return list;
        } catch (InvalidClassException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }
        return new TargetList();
    }

    public static void put(TargetList tl, Lookup lkp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(tl);
            oos.close();

            String buffer = new BASE64Encoder().encode(baos.toByteArray());

            ProjectProperties props = new ProjectProperties(lkp);
            props.put("favorite.targets", buffer);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
