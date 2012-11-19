/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.registry;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author n.specht
 */
public class TargetEntry implements Serializable {
    private String displayName = "";
    private String target = "";
    private String discription = "";
    private boolean favorite = false;
    private HashMap<String, String> parameter = new HashMap<String, String>();

    public TargetEntry(String t) {
        this.target = t;
        this.displayName = t;
    }

    public String getTarget() {
        return target;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void isFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public HashMap<String,String> getParameter() {
        return this.parameter;
    }
}
