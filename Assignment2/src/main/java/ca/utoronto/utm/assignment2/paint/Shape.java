package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Interface for all the shapes available in the application.
 */
public interface Shape {
    /**
     * Draws the shape.
     * @param g2d
     */
    void draw(GraphicsContext g2d);

    /**
     * Gets the color of the current shape
     * @return color of current shape
     */
    Color getColor();
}
