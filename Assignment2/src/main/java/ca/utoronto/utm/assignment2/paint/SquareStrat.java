package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareStrat implements DrawingStrat {
    private Square square;
    private Point startPoint;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth());
        square = new Square(startPoint, 0, model.getCurrentColor(), model.isFillMode(), model.getCurrentStrokeWidth());
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if (square != null && startPoint != null) {
            double endX = e.getX();
            double endY = e.getY();

            double dx = Math.abs(endX - startPoint.x);
            double dy = Math.abs(endY - startPoint.y);

            double side = Math.max(dx, dy);

            double x = startPoint.x;
            double y = startPoint.y;

            if (endX < startPoint.x) {
                x = startPoint.x - side;
            }
            if (endY < startPoint.y) {
                y = startPoint.y - side;
            }

            square.setTopLeft(new Point(x, y, model.getCurrentStrokeWidth()));
            square.setSide(side);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (square != null) {
            model.addShape(square);
            square = null;
            startPoint = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (this.square != null) {
            g2d.setStroke(this.square.getColor());
            double x = this.square.getTopLeft().x;
            double y = this.square.getTopLeft().y;
            double a = this.square.getSide();
            g2d.strokeRect(x, y, a, a);
        }

    }
}
