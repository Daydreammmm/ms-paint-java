package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class TriangleStrat implements DrawingStrat {
    private final PaintModel model;
    private int stage = 0;

    private double x1, y1;
    private double x2, y2;
    private double mx, my;

    public TriangleStrat(PaintModel model) {
        this.model = model;
    }

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        if (stage == 0) {
            x1 = e.getX(); y1 = e.getY();
            mx = x1; my = y1;
            stage = 1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (stage == 1) {
            x2 = e.getX(); y2 = e.getY();
            mx = x2; my = y2;
            stage = 2;
        } else if (stage == 2) {
            double x3 = e.getX(), y3 = e.getY();
            Triangle t = new Triangle(x1, y1, x2, y2, x3, y3, model.getCurrentColor(), model.isFillMode(), model.getCurrentStrokeWidth());
            this.model.addShape(t);
            stage = 0;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {
    }

    @Override
    public void drawMidConstruction(GraphicsContext g) {
        if (stage == 1) {
            g.strokeLine(x1, y1, mx, my);
        } else if (stage == 2) {
            double[] xs = {x1, x2, mx};
            double[] ys = {y1, y2, my};
            g.strokePolygon(xs, ys, 3);
        }
    }
}
