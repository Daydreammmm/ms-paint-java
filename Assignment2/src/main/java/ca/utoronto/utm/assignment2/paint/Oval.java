package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * An oval shape that can be drawn on a GraphicsContext.
 */
public class Oval implements Shape {
    private Point topLeft;
    private double width;
    private double height;
    private final Color color;
    private boolean filled;
    private double strokeWidth;

    /**
     * Create an oval with a top-left point, width, height, color, fill flag and stroke width.
     *
     * @param topLeft
     * @param width
     * @param height
     * @param color
     * @param filled
     * @param strokeWidth
     */
    public Oval(Point topLeft, double width, double height, Color color, boolean filled, double strokeWidth) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Draw this oval on the given GraphicsContext.
     *
     * @param g
     */
    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(strokeWidth);

        double x = topLeft.x;
        double y = topLeft.y;

        if (filled) {
            g.fillOval(x, y, width, height);
        } else {
            g.strokeOval(x, y, width, height);
        }
    }

    /**
     * Set the top-left point for this oval.
     *
     * @param topLeft
     */
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * Set the width for this oval.
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Set the height for this oval.
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the color of this oval.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the top-left point of this oval.
     *
     * @return the top-left point
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * Get the width of this oval.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the height of this oval.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get the stroke width for this oval.
     *
     * @return the stroke width
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }
}
