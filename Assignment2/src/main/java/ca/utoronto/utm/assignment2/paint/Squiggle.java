package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Squiggle implements Shape{
    private ArrayList<Point> points = new ArrayList<Point>();
    private Color color;
    private double strokeWidth;

    public Squiggle(Color color, double strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(GraphicsContext g2d) {
        g2d.setStroke(color);
        g2d.setLineWidth(strokeWidth);
        for(int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
