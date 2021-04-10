package org.paintFX.plugins.TrapeziumPlugin;

import javafx.scene.image.Image;
import org.paintFX.core.IService;
import org.paintFX.core.ShapeFactory;

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
    public ShapeFactory createFactory() {
        return new TrapeziumFactory();
    }
}