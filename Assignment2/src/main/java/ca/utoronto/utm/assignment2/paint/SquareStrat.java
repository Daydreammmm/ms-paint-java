package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareStrat implements DrawingStrat {
    private Square square;
    private Point startPoint;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY());
        this.square = new Square(startPoint, 0);
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if (square != null && startPoint != null) {
            double endX = e.getX();
            double endY = e.getY();

            double x = endX - startPoint.x;
            double y = endY - startPoint.y;
            double side =  Math.max(Math.abs(x), Math.abs(y));

            double tlx = startPoint.x + (x < 0 ? -side : 0);
            double tly = startPoint.y + (y < 0 ? -side : 0);

            this.square.setTopLeft(new Point(tlx, tly));
            this.square.setSide(side);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (this.square != null) {
            if (this.square.getSide() > 0) {
                model.addSquare(this.square);
            }
            this.square = null;
            this.startPoint = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (this.square != null) {
            g2d.setStroke(Color.PURPLE);
            double x = this.square.getTopLeft().x;
            double y = this.square.getTopLeft().y;
            double a = this.square.getSide();
            g2d.strokeRect(x, y, a, a);
        }

    }
}
