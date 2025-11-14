package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

/**
 * DrawingStrat interface for all various drawing modes.
 * Defines mouse events and the preview drawing for the different modes.
 */
public interface DrawingStrat {
    /**
     * Handle mousePressed events.
     * @param e
     * @param model
     */
    void mousePressed(MouseEvent e, PaintModel model);

    /**
     * Handles mouseReleased events.
     * @param e
     * @param model
     */
    void mouseReleased(MouseEvent e, PaintModel model);

    /**
     * Handles mouseMoved events.
     * @param e
     * @param model
     */
    void mouseMoved(MouseEvent e, PaintModel model);

    /**
     * Handles mouseClicked events.
     * @param e
     * @param model
     */
    void mouseClicked(MouseEvent e, PaintModel model);

    /**
     * Handles mouseDragged events.
     * @param e
     * @param model
     */
    void mouseDragged(MouseEvent e, PaintModel model);

    /**
     * Handles preview events.
     * @param g2d
     */
    void drawMidConstruction(GraphicsContext g2d);
}

