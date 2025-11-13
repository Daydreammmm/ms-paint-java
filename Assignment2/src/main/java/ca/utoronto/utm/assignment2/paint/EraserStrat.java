package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EraserStrat implements DrawingStrat {
    private Squiggle squiggle;
    private Boolean drawing = false;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        squiggle = new Squiggle(Color.web("#F5F5F5"), model.getCurrentStrokeWidth());
        drawing = true;
        System.out.println("Erasing");
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if(drawing && squiggle != null) {
            squiggle.addPoint(new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        model.addShape(squiggle);
        drawing = false;
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if(squiggle!=null && drawing){
            squiggle.draw(g2d);
        }
    }
}

