package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OvalStrat implements DrawingStrat {
    private Oval oval;
    private Point startPoint;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY());
        oval = new Oval(startPoint, 0, 0, model.getCurrentColor(), model.isFillMode());
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if (oval != null && startPoint != null) {
            double endX = e.getX();
            double endY = e.getY();

            double x = Math.min(startPoint.x, endX);
            double y = Math.min(startPoint.y, endY);
            double width = Math.abs(endX - startPoint.x);
            double height = Math.abs(endY - startPoint.y);

            oval.setTopLeft(new Point(x, y));
            oval.setWidth(width);
            oval.setHeight(height);
        }
    }

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

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (this.oval != null) {
            g2d.setStroke(oval.getColor());
            // show outline while dragging
            g2d.strokeOval(
                    this.oval.getTopLeft().x,
                    this.oval.getTopLeft().y,
                    this.oval.getWidth(),
                    this.oval.getHeight()
            );
        }
    }
}
