package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * A drawing strategy implementation for Polylines. The class handles all the mouse events and preview for the user to
 * create Polylines on the canvas.
 */
public class PolylineStrat implements DrawingStrat {
    private Polyline polyline;        // committed vertices
    private Point hoverPoint = null;  // current mouse for preview

    /**
     * Starts a new polyline at the mouse pressed position.
     * Left clicks add a vertex and right clicks finish a line.
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        if (e.getButton() == MouseButton.PRIMARY) { // left-click adds a vertex
            if (polyline == null) polyline = new Polyline(model.getCurrentColor(), model.getCurrentStrokeWidth());
            polyline.addPoint(new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth()));
        } else if (e.getButton() == MouseButton.SECONDARY) { // right-click finishes
            finish(model);
        }
    }

    /**
     * Updates the polyline based on the mouseDragged event.
     * @param e
     * @param m
     */
    @Override public void mouseDragged(MouseEvent e, PaintModel m){
        hoverPoint = new Point(e.getX(), e.getY(), m.getCurrentStrokeWidth());
    }

    /**
     * Updates the line during mouse movement.
     * @param e
     * @param m
     */
    @Override public void mouseMoved(MouseEvent e, PaintModel m){
        hoverPoint = new Point(e.getX(), e.getY(), m.getCurrentStrokeWidth());
    }

    @Override public void mouseReleased(MouseEvent e, PaintModel m){ }

    /**
     * Double clicks that finish the polyline.
     * @param e
     * @param m
     */
    @Override public void mouseClicked(MouseEvent e, PaintModel m){
        if (e.getClickCount()==2) {
            finish(m);
        }
    }

    /**
     * Adds the final poluyline to the canvas and resets the current polyline to null.
     * @param model
     */
    private void finish(PaintModel model) {
        if (polyline != null && polyline.getPoints().size() >= 2) model.addShape(polyline);
        polyline = null; hoverPoint = null;
    }

    /**
     * Draws the polyline mid-construction so that the user can see the shape they are adding to the canvas.
     * @param g
     */
    @Override
    public void drawMidConstruction(GraphicsContext g) {
        if (polyline == null) return;
        polyline.draw(g);
        if (hoverPoint != null && !polyline.getPoints().isEmpty()) {
            Point last = polyline.getPoints().get(polyline.getPoints().size()-1);
            g.setStroke(polyline.getColor());
            g.setLineWidth(polyline.getStrokeWidth());
            g.strokeLine(last.x, last.y, hoverPoint.x, hoverPoint.y);
        }
    }
}

