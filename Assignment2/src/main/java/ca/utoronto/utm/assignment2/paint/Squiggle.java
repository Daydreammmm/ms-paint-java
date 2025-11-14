package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A free-hand squiggle that can be drawn on a GraphicsContext.
 */
public class Squiggle implements Shape{
    private ArrayList<Point> points = new ArrayList<Point>();
    private Color color;
    private double strokeWidth;

    /**
     * Creates a new squiggle with the given color and strokeWidth.
     * @param color
     * @param strokeWidth
     */
    public Squiggle(Color color, double strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Adds a point to the squiggle.
     * @param point
     */
    public void addPoint(Point point) {
        this.points.add(point);
    }

    /**
     * Returns all points in the squiggle.
     * @return Arraylist<Point>
     */
    public ArrayList<Point> getPoints() {
        return this.points;
    }

    /**
     * Returns the current color of the squiggle.
     * @return color of the squiggle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the squiggle to color.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the squiggle on a given GraphicsContext.
     * @param g2d
     */
    public void draw(GraphicsContext g2d) {
        g2d.setStroke(color);
        g2d.setLineWidth(strokeWidth);
        for(int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
