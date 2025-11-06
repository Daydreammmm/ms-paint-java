package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquiggleStrat implements DrawingStrat {
    private Squiggle squiggle;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        squiggle = new Squiggle(model.getCurrentColor());
        System.out.println("Started Squiggle");
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        squiggle.addPoint(new Point(e.getX(), e.getY()));
        model.addShape(squiggle);
        System.out.println("Added Squiggle");
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

