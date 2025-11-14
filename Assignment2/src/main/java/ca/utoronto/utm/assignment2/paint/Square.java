package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A square shape that can be drawn on a GraphicsContext.
 */
public class Square implements Shape {
    private Point topLeft;
    private double side;
    private final Color color;
    private boolean filled;
    private final double strokeWidth;

    /**
     * Create a square with a top-left point, side length, color, fill flag and stroke width.
     *
     * @param topLeft
     * @param side
     * @param color
     * @param filled
     * @param strokeWidth
     */
    public Square(Point topLeft, double side, Color color, boolean filled, double strokeWidth) {
        this.topLeft = topLeft;
        this.side = side;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Get the top-left point of this square.
     *
     * @return top-left point
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * Set the top-left point of this square.
     *
     * @param p
     */
    public void setTopLeft(Point p) {
        this.topLeft = p;
    }

    /**
     * Get the side length of this square.
     *
     * @return side length
     */
    public double getSide() {
        return side;
    }

    /**
     * Set the side length of this square.
     *
     * @param s
     */
    public void setSide(double s) {
        this.side = s;
    }

    /**
     * Get the color of this square.
     *
     * @return color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Return whether this square is filled.
     *
     * @return true if filled, false if outline
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Get the stroke width used to draw this square.
     *
     * @return stroke width
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Draw this square on the given GraphicsContext.
     *
     * @param g
     */
    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(strokeWidth);

        double x = topLeft.x;
        double y = topLeft.y;
        double s = side;

        if (s < 0) {
            x += s;
            s = -s;
        }

        if (filled) {
            g.fillRect(x, y, s, s);
        } else {
            g.strokeRect(x, y, s, s);
        }
    }
}
