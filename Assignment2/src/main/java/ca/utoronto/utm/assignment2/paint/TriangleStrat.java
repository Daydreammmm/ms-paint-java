package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * click first, drag+release to second (shows a line),
 * then drag+release to third (shows live triangle), done.
 */
public class TriangleStrat implements DrawingStrat {
    private final PaintModel model;

    // 0 -> waiting for first
    // 1 -> got first, dragging/releasing to second
    // 2 -> got second, dragging/releasing to third
    private int stage = 0;

    private double x1, y1;
    private double x2, y2;
    private double mx, my; // current mouse for preview

    public TriangleStrat(PaintModel model) {
        this.model = model;
    }

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        // we treat press/release pairs as the "confirm" step
        if (stage == 0) {
            x1 = e.getX(); y1 = e.getY();
            mx = x1; my = y1;
            stage = 1;
        }
        // no-op for stage 1/2 on press; we finalize on release
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if (stage == 1) {
            // confirm second vertex
            x2 = e.getX(); y2 = e.getY();
            mx = x2; my = y2;
            stage = 2;
        } else if (stage == 2) {
            // confirm third vertex and commit the shape
            double x3 = e.getX(), y3 = e.getY();
            Triangle t = new Triangle(x1, y1, x2, y2, x3, y3, model.getCurrentColor(), model.isFillMode());
            this.model.addShape(t);
            // reset for the next triangle
            stage = 0;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        // just store current mouse to drive the preview
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {
        // don't need this for now; could show a ghost cursor later
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {
        // we’re using press/release flow, so ignore click
    }

    @Override
    public void drawMidConstruction(GraphicsContext g) {
        // draw the "in-progress" preview so the user sees what’s happening
        if (stage == 1) {
            // have first (x1,y1), preview a line to current mouse (mx,my)
            g.strokeLine(x1, y1, mx, my);
        } else if (stage == 2) {
            // have first two vertices, preview triangle with current mouse
            double[] xs = {x1, x2, mx};
            double[] ys = {y1, y2, my};
            g.strokePolygon(xs, ys, 3);
        }
    }
}
