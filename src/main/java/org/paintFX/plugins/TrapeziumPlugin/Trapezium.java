package org.paintFX.plugins.TrapeziumPlugin;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.*;

import java.util.List;

public class Trapezium extends Shape {

    private double[] pointsX;
    private double[] pointsY;

    private boolean horizontal;
    private boolean small;

    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    private double calculateK(double x1, double y1, double x2, double y2) {
        return (y2 - y1) / (x2 - x1);
    }

    private double calculatePoint(double k, double x1, double y1, double x2) {
        return k * (x2 - x1) + y1;
    }

    public Trapezium(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        super(borderSize, fillColor, borderColor, paintMode);
        requiredPointsCount = 4;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());
        g.setFill(fillColor.getFXColor());

        int len = pointsX.length;

        switch (paintMode) {
            case FILLED -> g.fillPolygon(pointsX, pointsY, len);
            case BORDERED -> g.strokePolygon(pointsX, pointsY, len);
            case FILLED_WITH_BORDER -> {
                g.fillPolygon(pointsX, pointsY, len);
                g.strokePolygon(pointsX, pointsY, len);
            }
            default -> System.out.println("Unknown paint mode");
        }

    }

    @Override
    public void setPoints(List<Point> points) {

        switch (points.size()) {
            case 2 -> {
                pointsX = new double[] { points.get(0).getX(), points.get(1).getX() };
                pointsY = new double[] { points.get(0).getY(), points.get(1).getY() };
            }
            case 3 -> {
                double dx = Math.abs(points.get(0).getX() - points.get(1).getX());
                double dy = Math.abs(points.get(0).getY() - points.get(1).getY());

                horizontal =  dx > dy;
                small = horizontal ? points.get(2).getX() > points.get(0).getX() : points.get(2).getY() > points.get(0).getY();
            }
            case 4 -> {
                double k, y, x;

                if (horizontal) {
                    points.get(3).setX(small ?
                            clamp(points.get(3).getX(), 0, points.get(2).getX() - borderSize) :
                            clamp(points.get(3).getX(), points.get(2).getX() + borderSize, Integer.MAX_VALUE
                            ));

                    k = calculateK(points.get(0).getX(), points.get(0).getY(), points.get(1).getX(), points.get(1).getY());
                    y = calculatePoint(k, points.get(2).getX(), points.get(2).getY(), points.get(3).getX());

                    points.get(3).setY(y);
                } else {
                    points.get(3).setY(small ?
                            clamp(points.get(3).getY(), 0, points.get(2).getY() - borderSize) :
                            clamp(points.get(3).getY(), points.get(2).getY() + borderSize, Integer.MAX_VALUE
                            ));

                    k = calculateK(points.get(0).getY(), points.get(0).getX(), points.get(1).getY(), points.get(1).getX());
                    x = calculatePoint(k, points.get(2).getY(), points.get(2).getX(), points.get(3).getY());

                    points.get(3).setX(x);
                }

            }
            default -> System.out.println("Unexpected number of points received");

        }

        pointsX = new double[points.size()];
        pointsY = new double[points.size()];

        for (int i = 0; i < points.size(); i++) {
            pointsX[i] = points.get(i).getX();
            pointsY[i] = points.get(i).getY();
        }

    }
}