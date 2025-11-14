package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A drawing strategy implementation for Rectangles. The class handles all the mouse events and preview for the user to
 * create rectangles on the canvas.
 */

public class RectangleStrat implements DrawingStrat {
    private Rectangle rectangle;
    private Point startPoint;

    /**
     * Creates a new rectangle with the first coordinate at the mouse pressed position.
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth());
        rectangle = new Rectangle(startPoint, 0, 0, model.getCurrentColor(), model.isFillMode(), model.getCurrentStrokeWidth());
    }

    /**
     * Updates the four coordinates of the rectangle during mouseDragged events.
     * @param e
     * @param model
     */
    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if (rectangle != null && startPoint != null) {
            double endX = e.getX();
            double endY = e.getY();

            double x = Math.min(startPoint.x, endX);
            double y = Math.min(startPoint.y, endY);
            double width = Math.abs(endX - startPoint.x);
            double height = Math.abs(endY - startPoint.y);

            this.rectangle.setTopLeft(new Point(x, y, model.getCurrentStrokeWidth()));
            this.rectangle.setWidth(width);
            this.rectangle.setHeight(height);
        }
    }

    /**
     * Adds the finalized rectangle to the canvas and resets the current rectangle reference to null.
     * @param e
     * @param model
     */
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (rectangle != null) {
            model.addShape(this.rectangle);
            this.rectangle = null;
            this.startPoint = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    /**
     * Displays the rectangle mid-construction so the user can see the rectangle they will add to the canvas.
     * @param g2d
     */
    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (this.rectangle!=null){
            g2d.setStroke(this.rectangle.getColor());
            g2d.strokeRect(this.rectangle.getTopLeft().x, this.rectangle.getTopLeft().y,
                    this.rectangle.getWidth(), this.rectangle.getHeight());

        }
    }
}
