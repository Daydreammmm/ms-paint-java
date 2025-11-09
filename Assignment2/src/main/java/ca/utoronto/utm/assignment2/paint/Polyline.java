package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Polyline implements Shape {
    private final List<Point> points = new ArrayList<>();
    private final Color color;
    private final double strokeWidth;

    public Polyline(Color color, double strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public List<Point> getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void draw(GraphicsContext g) {
        if (points.size() < 2) return;
        g.setStroke(color);
        g.setLineWidth(strokeWidth);
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i), b = points.get(i + 1);
            g.strokeLine(a.x, a.y, b.x, b.y);
        }
    }
}
