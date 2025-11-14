package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A drawing strategy implementation for Ovals. The class handles all the mouse events and preview for the user to
 * create ovals on the canvas.
 */
public class OvalStrat implements DrawingStrat {
    private Oval oval;
    private Point startPoint;

    /**
     * Creates a new oval at the mousePressed position.
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth());
        oval = new Oval(startPoint, 0, 0, model.getCurrentColor(), model.isFillMode(), model.getCurrentStrokeWidth());
    }

    /**
     * Updates the oval based on the mouseDragged event.
     * @param e
     * @param model
     */
    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if (oval != null && startPoint != null) {
            double endX = e.getX();
            double endY = e.getY();

            double x = Math.min(startPoint.x, endX);
            double y = Math.min(startPoint.y, endY);
            double width = Math.abs(endX - startPoint.x);
            double height = Math.abs(endY - startPoint.y);

            oval.setTopLeft(new Point(x, y, model.getCurrentStrokeWidth()));
            oval.setWidth(width);
            oval.setHeight(height);
        }
    }

    /**
     * Adds the finalized ovals to the canvas and resets the current circle reference to null.
     * @param e
     * @param model
     */
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (oval != null) {
            model.addShape(oval);
            oval = null;
            startPoint = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    /**
     * Displays the ovals mid-construction so the user can see the ovals they will add to the canvas.
     * @param g2d
     */
    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (this.oval != null) {
            g2d.setStroke(oval.getColor());
            g2d.strokeOval(
                    this.oval.getTopLeft().x,
                    this.oval.getTopLeft().y,
                    this.oval.getWidth(),
                    this.oval.getHeight()
            );
            g2d.setLineWidth(oval.getStrokeWidth());
        }
    }
}
