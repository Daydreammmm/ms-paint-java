package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PolylineStrat implements DrawingStrat {
    private Polyline polyline;        // committed vertices
    private Point hoverPoint = null;  // current mouse for preview

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        if (e.getButton() == MouseButton.PRIMARY) { // left-click adds a vertex
            if (polyline == null) polyline = new Polyline(model.getCurrentColor(), model.getCurrentStrokeWidth());
            polyline.addPoint(new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth()));
        } else if (e.getButton() == MouseButton.SECONDARY) { // right-click finishes
            finish(model);
        }
    }

    @Override public void mouseDragged(MouseEvent e, PaintModel m){
        hoverPoint = new Point(e.getX(), e.getY(), m.getCurrentStrokeWidth());
    }

    @Override public void mouseMoved(MouseEvent e, PaintModel m){
        hoverPoint = new Point(e.getX(), e.getY(), m.getCurrentStrokeWidth());
    }

    @Override public void mouseReleased(MouseEvent e, PaintModel m){ }

    @Override public void mouseClicked(MouseEvent e, PaintModel m){
        if (e.getClickCount()==2) {
            finish(m);
        }
    }

    private void finish(PaintModel model) {
        if (polyline != null && polyline.getPoints().size() >= 2) model.addShape(polyline);
        polyline = null; hoverPoint = null;
    }

    @Override
    public void drawMidConstruction(GraphicsContext g) {
        if (polyline == null) return;
        polyline.draw(g);
        if (hoverPoint != null && !polyline.getPoints().isEmpty()) {
            Point last = polyline.getPoints().get(polyline.getPoints().size()-1);
            g.setStroke(polyline.getColor());
            g.setLineWidth(model.getCurrentStrokeWidth());
            g.strokeLine(last.x, last.y, hoverPoint.x, hoverPoint.y);
        }
    }
}

