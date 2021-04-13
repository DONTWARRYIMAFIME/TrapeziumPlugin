import org.paintFX.core.IService;
import org.paintFX.trapezium.Service;

module Trapezium {
    requires javafx.controls;
    requires core;

    exports org.paintFX.trapezium to PaintFX;
    provides IService with Service;
}