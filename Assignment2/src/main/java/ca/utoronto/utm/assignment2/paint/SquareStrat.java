package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Drawing strategy for creating squares on the canvas.
 */
public class SquareStrat implements DrawingStrat {
    private Square square;
    private Point startPoint;

    /**
     * Handle mouse press to start drawing a new square.
     *
     * @param e     mouse event with starting position
     * @param model current PaintModel
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        startPoint = new Point(e.getX(), e.getY(), model.getCurrentStrokeWidth());
        square = new Square(startPoint, 0, model.getCurrentColor(), model.isFillMode(), model.getCurrentStrokeWidth());
    }

    /**
     * Handle mouse drag to update the square's size and position.
     *
     * @param e     mouse event with current position
     * @param model current PaintModel
     */
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

    /**
     * Handle mouse release to finish the square and add it to the model.
     *
     * @param e     mouse event
     * @param model current PaintModel
     */
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (square != null) {
            model.addShape(square);
            square = null;
            startPoint = null;
        }
    }

    /**
     * Handle mouse move.
     *
     * @param e     mouse event
     * @param model current PaintModel
     */
    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    /**
     * Handle mouse click.
     *
     * @param e     mouse event
     * @param model current PaintModel
     */
    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    /**
     * Draw the square while it is being constructed (during dragging).
     *
     * @param g2d graphics context to draw on
     */
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
