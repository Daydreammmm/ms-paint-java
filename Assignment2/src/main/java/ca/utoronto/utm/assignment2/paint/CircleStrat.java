package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A drawing strategy implementation for Circles. The class handles all the mouse events and preview for the user to
 * create circles on the canvas.
 */
public class CircleStrat implements DrawingStrat{

    private Circle circle;

    /**
     * Creates a new circle with the centre as the mousePressed position.
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Circle");
        Point centre = new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth());
        circle = new Circle(centre, 0, model.getCurrentColor(), model.isFillMode(), model.getCurrentStrokeWidth());
    }

    /**
     * Updates the radius of the circle based on the mouseDragged event.
     * @param e
     * @param model
     */
    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if(circle != null) {
            double radius = Math.sqrt(Math.pow((e.getX() - this.circle.getCentre().x), 2) +
                    Math.pow((e.getY() - this.circle.getCentre().y), 2));
            circle.setRadius(radius);
        }
    }

    /**
     * Adds the finalized circle to the canvas and resets the current circle reference to null.
     * @param e
     * @param model
     */
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if(circle != null) {
            model.addShape(circle);
            System.out.println("Added circle");
            circle = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    /**
     * Displays the circle mid-construction so the user can see the circle they will add to the canvas.
     * @param g2d
     */
    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (circle != null) {
            double x = circle.getCentre().x;
            double y = circle.getCentre().y;
            double r = circle.getRadius();
            g2d.setStroke(circle.getColor());
            g2d.strokeOval(x - r, y - r, r * 2, r * 2);
        }
    }

}
