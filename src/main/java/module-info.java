import org.paintFX.core.IService;
import org.paintFX.plugins.TrapeziumPlugin.Service;

module Trapezium {
    requires javafx.controls;
    requires core;

    exports org.paintFX.plugins.TrapeziumPlugin;
    provides IService with Service;
}