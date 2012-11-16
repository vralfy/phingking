/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.registry;

/**
 *
 * @author n.specht
 */
public class TargetEntry {
    private String target = "";
    private String discription = "";

    public TargetEntry(String t) {
        this.target = t;
    }

    public String getTarget() {
        return target;
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

}
