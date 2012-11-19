/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

/**
 *
 * @author n.specht
 */
public class RunThread implements Runnable {

    RunTarget target;

    public RunThread(RunTarget t) {
        this.target = t;
    }

    @Override
    public void run() {
        this.target.run();
    }

}
