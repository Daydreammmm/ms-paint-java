package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A rectangle shape that can be drawn on a GraphicsContext
 */
public class Rectangle implements Shape {
    private Point topLeft;
    private double width;
    private double height;
    private final Color color;
    private boolean filled;
    private double strokeWidth;

    /**
     * Creates a  new rectangle with the specified properties.
     * @param topLeft
     * @param width
     * @param height
     * @param color
     * @param filled
     * @param strokeWidth
     */
    public Rectangle(Point topLeft, double width, double height, Color color, boolean filled, double strokeWidth) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Returns the top left corner point of the rectangle.
     * @return
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * Sets the top left corner point of the rectangle to topLeft.
     * @param topLeft
     */
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * Returns the width of the rectangle.
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle to width.
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Returns the height of the rectangle.
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle to height.
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the color of the rectangle.
     * @return color of the rectangle
     */
    public Color getColor(){
        return color;
    }

    /**
     * Retrurns whether the rectangle is filled.
     * @return true or false
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Returns the widh of the current stroke.
     * @return width of stroke
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Sets whether the rectangle is filled or not.
     * @param filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Draws the rectangle.
     * @param g
     */
    @Override
    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(strokeWidth);

        double x = topLeft.x;
        double y = topLeft.y;
        double w = width;
        double h = height;

        if (w < 0) {
            x += w;
            w = -w;
        }
        if (h < 0) {
            y += h;
            h = -h;
        }

        if (filled) {
            g.fillRect(x, y, w, h);
        } else {
            g.strokeRect(x, y, w, h);
        }
    }

}
