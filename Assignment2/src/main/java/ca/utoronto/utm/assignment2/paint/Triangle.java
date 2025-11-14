package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A triangle shape that can be drawn on a GraphicsContext.
 */
public class Triangle implements Shape {
    private final double x1, y1;
    private final double x2, y2;
    private final double x3, y3;
    private final Color color;
    private boolean filled;
    private final double strokeWidth;

    /**
     * Create a triangle with three vertices, color, fill flag and stroke width.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param color
     * @param filled
     * @param strokeWidth
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, Color color, boolean filled, double strokeWidth) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Draw this triangle on the given GraphicsContext.
     *
     * @param g
     */
    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(strokeWidth);

        double[] xPoints = {x1, x2, x3};
        double[] yPoints = {y1, y2, y3};

        if (filled) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.strokePolygon(xPoints, yPoints, 3);
        }
    }

    /**
     * Get the color of this triangle.
     *
     * @return color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Return whether this triangle is filled.
     *
     * @return true if filled, false if outline
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Get the stroke width used to draw this triangle.
     *
     * @return stroke width
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }
}
