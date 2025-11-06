package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
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

    public void draw(GraphicsContext g2d) {
        g2d.setStroke(color);
        g2d.setFill(color);
        double x = this.getTopLeft().x;
        double y = this.getTopLeft().y;
        double a = this.getSide();
        g2d.strokeRect(x, y, a, a);
        g2d.fillRect(x, y, a, a);
    }
}
