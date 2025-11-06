package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

public class Square {
    private Point topLeft;
    private double side;
    private final Color color;

    public Square(Point topLeft, double side, Color color) {
        this.topLeft = topLeft;
        this.side = side;
        this.color = color;
    }

    public Point getTopLeft() { return topLeft; }
    public void setTopLeft(Point p) { this.topLeft = p; }

    public double getSide() { return side; }
    public void setSide(double s) { this.side = s; }

    public Color getColor(){
        return color;
    }
}
