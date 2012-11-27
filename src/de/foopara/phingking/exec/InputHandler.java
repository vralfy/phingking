/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.exec;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author n.specht
 */
public class InputHandler {

    private File src;
    private UUID id = UUID.randomUUID();

    public InputHandler(File configPath) {
        try {
            this.src = new File(configPath.getParentFile(), this.getHandlerClass() + ".php");
            this.src.createNewFile();
            FileWriter f = new FileWriter(this.src);
            f.write(this.toString());
            f.flush();
            f.close();
        } catch (IOException ex) {
        }
    }

    public String getHandlerClass() {
        return "PhingKingInputHandler" + this.id.toString().replaceAll("-", "");
    }

    public String getInputMarker() {
        return "---" + this.getHandlerClass() + "---";
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff
            .append("<?php\n")
            .append("require_once 'phing/input/DefaultInputHandler.php';\n")
            .append("class ").append(this.getHandlerClass()).append(" extends DefaultInputHandler {\n")
            .append("protected function getPrompt(InputRequest $request){\n")
            .append("return '").append(this.getInputMarker()).append("' . parent::getPrompt($request);\n")
            .append("}\n")
            .append("}");

        return buff.toString();
    }

    public void delete()
    {
        this.src.delete();
    }
}
