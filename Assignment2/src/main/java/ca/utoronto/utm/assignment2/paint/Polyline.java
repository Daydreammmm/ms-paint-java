package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A polyline shape made of connected points.
 */
public class Polyline implements Shape {
    private final List<Point> points = new ArrayList<>();
    private final Color color;
    private final double strokeWidth;

    /**
     * Create a polyline with a color and stroke width.
     *
     * @param color
     * @param strokeWidth
     */
    public Polyline(Color color, double strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Add a point to this polyline.
     *
     * @param p
     */
    public void addPoint(Point p) {
        points.add(p);
    }

    /**
     * Get the list of points in this polyline.
     *
     * @return list of points
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Get the color of this polyline.
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the stroke width for this polyline.
     *
     * @return stroke width
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Draw this polyline on the given GraphicsContext.
     *
     * @param g
     */
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
