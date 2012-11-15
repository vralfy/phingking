/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phingking.options.ui;

import javax.swing.JComponent;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class PropertyProvider implements ProjectCustomizer.CompositeCategoryProvider {

    @ProjectCustomizer.CompositeCategoryProvider.Registration(projectType = "org-netbeans-modules-php-project", position = 901)
//    @ProjectCustomizer.CompositeCategoryProvider.Registration(projectType = "org-netbeans-api-project", position = 900)
    public static PropertyProvider createProperties() {
        return new PropertyProvider();
    }

    @Override
    public Category createCategory(Lookup context) {
        //ResourceBundle bundle = NbBundle.getBundle(NavigatorTopComponent.class);
        return ProjectCustomizer.Category.create("phingking", "PhingKing", null);
    }

    @Override
    public JComponent createComponent(Category category, Lookup context) {
        PropertiesPanel panel = new PropertiesPanel(context);
        PropertiesOKAction act = new PropertiesOKAction();
        act.setPanel(panel);
        category.setOkButtonListener(act);
        return panel;
    }

}
