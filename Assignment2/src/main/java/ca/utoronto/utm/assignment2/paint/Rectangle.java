package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Shape {
    private Point topLeft;
    private double width;
    private double height;
    private final Color color;
    private boolean filled;
    private double strokeWidth;

    public Rectangle(Point topLeft, double width, double height, Color color, boolean filled, double strokeWidth) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
        this.strokeWidth = strokeWidth;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(strokeWidth);

        double x = topLeft.x;
        double y = topLeft.y;
        double w = width;
        double h = height;

        if (w < 0) {
            x += w;
            w = -w;
        }
        if (h < 0) {
            y += h;
            h = -h;
        }

        if (filled) {
            g.fillRect(x, y, w, h);
        } else {
            g.strokeRect(x, y, w, h);
        }
    }

}
