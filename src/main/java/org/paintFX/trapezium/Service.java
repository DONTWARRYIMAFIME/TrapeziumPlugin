package org.paintFX.trapezium;

import javafx.scene.image.Image;
import org.paintFX.core.*;
import org.paintFX.trapezium.PluginContext;

public class Service implements IService {
    @Override
    public String getToolName() {
        return "Trapezium";
    }

    @Override
    public Image getIcon() {
        return PluginContext.loadImage("/trapezium.png");
    }

    @Override
    public Shape createShape(double v, SColor sColor, SColor sColor1, PaintMode paintMode) {
        return new Trapezium(v, sColor, sColor1, paintMode);
    }

}