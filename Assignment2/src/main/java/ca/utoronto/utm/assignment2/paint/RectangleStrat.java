package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RectangleStrat implements DrawingStrat {
    private Rectangle rectangle;
    private Point startPoint;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY());
        this.rectangle = new Rectangle(startPoint, 0, 0);
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if (rectangle != null && startPoint != null) {
            double endX = e.getX();
            double endY = e.getY();

            double x = Math.min(startPoint.x, endX);
            double y = Math.min(startPoint.y, endY);
            double width = Math.abs(endX - startPoint.x);
            double height = Math.abs(endY - startPoint.y);

            this.rectangle.setTopLeft(new Point(x, y));
            this.rectangle.setWidth(width);
            this.rectangle.setHeight(height);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (rectangle != null) {
            model.addRectangle(this.rectangle);
            this.rectangle = null;
            this.startPoint = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (this.rectangle!=null){
            g2d.setStroke(Color.BLUE);
            g2d.strokeRect(this.rectangle.getTopLeft().x, this.rectangle.getTopLeft().y,
                    this.rectangle.getWidth(), this.rectangle.getHeight());

        }
    }
}
