package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
    private Point topLeft;
    private double side;
    private final Color color;
    private boolean filled;
    private final double strokeWidth;

    public Square(Point topLeft, double side, Color color, boolean filled, double strokeWidth) {
        this.topLeft = topLeft;
        this.side = side;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point p) {
        this.topLeft = p;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double s) {
        this.side = s;
    }

    public Color getColor(){
        return color;
    }

    public boolean isFilled() {
        return filled;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(strokeWidth);

        double x = topLeft.x;
        double y = topLeft.y;
        double s = side;

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
