package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Oval implements Shape {
    private Point topLeft;
    private double width;
    private double height;
    private final Color color;
    private boolean filled;
    private double strokeWidth;

    public Oval(Point topLeft, double width, double height, Color color, boolean filled, double strokeWidth) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);

        double x = topLeft.x;
        double y = topLeft.y;

        if (filled) {
            g.fillOval(x, y, width, height);
        } else {
            g.strokeOval(x, y, width, height);
        }
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Paint getColor() {
        return color;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }
}
