package org.paintFX.plugins.TrapeziumPlugin;

import org.paintFX.core.PaintMode;
import org.paintFX.core.SColor;
import org.paintFX.core.Shape;
import org.paintFX.core.ShapeFactory;

public class TrapeziumFactory implements ShapeFactory {
    @Override
    public Shape createShape(double v, SColor sColor, SColor sColor1, PaintMode paintMode) {
        return new Trapezium(v, sColor, sColor1, paintMode);
    }
}
