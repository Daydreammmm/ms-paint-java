package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * super simple triangle with 3 vertices and a draw() helper.
 * feel free to extend later with stroke width, fill toggle, etc.
 */
public class Triangle {
    private final double x1, y1;
    private final double x2, y2;
    private final double x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
    }

    public void draw(GraphicsContext g) {
        // outline only for now (matches how our other shapes look)
        double[] xs = {x1, x2, x3};
        double[] ys = {y1, y2, y3};
        g.setStroke(Color.BLACK);
        g.strokePolygon(xs, ys, 3);
        // if you want fill later:
        // g.setFill(Color.color(0,0,0,0)); // transparent for now
        // g.fillPolygon(xs, ys, 3);
    }
}
