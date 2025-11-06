package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
    private Point topLeft;
    private double side;
    private final Color color;
    private boolean filled;

    public Square(Point topLeft, double side, Color color, boolean filled) {
        this.topLeft = topLeft;
        this.side = side;
        this.color = color;
        this.filled = filled;
    }

    public Point getTopLeft() { return topLeft; }
    public void setTopLeft(Point p) { this.topLeft = p; }

    public double getSide() { return side; }
    public void setSide(double s) { this.side = s; }

    public Color getColor(){
        return color;
    }

    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);

        double x = topLeft.x;
        double y = topLeft.y;
        double s = side;

        // handle negative side (dragging upwards/left)
        if (s < 0) {
            x += s;
            s = -s;
        }

        if (filled) {
            g.fillRect(x, y, s, s);
        } else {
            g.strokeRect(x, y, s, s);
        }
    }
}
