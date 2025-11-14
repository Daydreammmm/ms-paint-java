package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * An eraser implementation that can erase shapes on the canvas in both dark and light mode.
 */
public class EraserStrat implements DrawingStrat {
    private Squiggle squiggle;
    private Boolean drawing = false;

    /**
     * Starts the eraser
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        boolean dmode = model.getDarkMode();
        if (dmode) {
            squiggle = new Squiggle(Color.web("#1E1E1E"), model.getCurrentStrokeWidth());
        }
        else{
            squiggle = new Squiggle(Color.web("#F5F5F5"), model.getCurrentStrokeWidth());
        }
        drawing = true;
        System.out.println("Erasing");
    }

    /**
     * Adds erase squiggles while dragging.
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
     * Finishes erasing
     * @param e
     * @param model
     */
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        model.addShape(squiggle);
        drawing = false;
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    /**
     * Draws the erase preview.
     * @param g2d
     */
    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if(squiggle!=null && drawing){
            squiggle.draw(g2d);
        }
    }
}

