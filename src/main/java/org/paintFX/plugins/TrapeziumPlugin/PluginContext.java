package org.paintFX.plugins.TrapeziumPlugin;

import javafx.scene.image.Image;

public class PluginContext {

    public static Image loadImage(String path) {
        Image image = null;
        try {
            image = new Image(PluginContext.class.getResource(path).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;


    }
}
