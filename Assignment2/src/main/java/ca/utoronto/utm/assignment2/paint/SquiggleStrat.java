package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A drawing strategy implementation for Squiggles. The class handles all the mouse events and preview for the user to
 * create squiggles on the canvas.
 */
public class SquiggleStrat implements DrawingStrat {
    private Squiggle squiggle;
    private Boolean drawing = false;

    /**
     * Starts a new squiggle at the mousePressed position.
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        squiggle = new Squiggle(model.getCurrentColor(), model.getCurrentStrokeWidth());
        drawing = true;
        System.out.println("Started Squiggle");
    }

    /**
     * Adds points to the squiggle during mouseDragged events.
     * @param e
     * @param model
     */
    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if(drawing && squiggle != null) {
            squiggle.addPoint(new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth()));
        }
    }

    /**
     * Adds the finalized squiggle to the canvas.
     * @param e
     * @param model
     */
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        model.addShape(squiggle);
        drawing = false;
        System.out.println("Added Squiggle");
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    /**
     * Displays the squiggle mid-construction so the user can see the squiggle they will add to the canvas.
     * @param g2d
     */
    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if(squiggle!=null && drawing){
            squiggle.draw(g2d);
        }
    }
}

