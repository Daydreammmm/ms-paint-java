package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval implements Shape {
    private Point topLeft;
    private double width;
    private double height;
    private final Color color;

    public Oval(Point topLeft, double width, double height, Color color) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
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

    public void draw(GraphicsContext g2d) {
        g2d.setStroke(color);
        g2d.setFill(color);
        double x = this.getTopLeft().x;
        double y = this.getTopLeft().y;
        double width = this.getWidth();
        double height = this.getHeight();
        g2d.strokeOval(x, y, width, height);
        g2d.fillOval(x, y, width, height);
    }
}
