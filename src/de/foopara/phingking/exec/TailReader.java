/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.openide.util.Exceptions;
import org.openide.windows.InputOutput;

/**
 *
 * @author n.specht
 */
public class TailReader extends Thread {
    private InputOutput io;
    private File log;
    private boolean keepReading = true;
    private boolean inProgess;
    public TailReader(InputOutput io, File log) {
        this.io = io;
        this.log = log;
    }

    @Override
    public void run() {
        this.inProgess = true;
        try {
            BufferedReader br = null;
            while(this.keepReading) {
                if (br == null && this.log.exists()) {
                    br = new BufferedReader(new FileReader(this.log));
                }

                if (br != null) {
                    String line = br.readLine();
                    if (line == null) {
                        Thread.sleep(200);
                    } else {
                        this.io.getOut().write(line + "\n");
                        this.io.getOut().flush();
                    }
                }
            }
            // Und den Rest auslesen
            String line = br.readLine();
            while(line != null) {
                this.io.getOut().write(line + "\n");
                this.io.getOut().flush();
                line = br.readLine();
            }
            br.close();

        } catch (InterruptedException ex) {
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
        }
        this.inProgess = false;
    }

    public void stopReading() {
        this.keepReading = false;
        try {
            while(this.inProgess) {
                Thread.sleep(200);
            }
        } catch (InterruptedException ex) {
        }
    }
}
