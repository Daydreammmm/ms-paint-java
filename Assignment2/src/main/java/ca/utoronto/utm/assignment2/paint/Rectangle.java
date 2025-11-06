package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

public class Rectangle {
    private Point topLeft;
    private double width;
    private double height;
    private final Color color;

    public Rectangle(Point topLeft, double width, double height, Color color) {
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
}
