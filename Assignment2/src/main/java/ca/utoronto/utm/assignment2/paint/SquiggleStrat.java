package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquiggleStrat implements DrawingStrat {

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        model.addPoint(new Point(Double.NaN, Double.NaN));
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        model.addPoint(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {}
}

