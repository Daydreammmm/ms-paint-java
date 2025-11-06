package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Shape {
        private Point centre;
        private double radius;
        private Color color;
        private boolean filled; // new flag

        public Circle(Point centre, double radius, Color color, boolean filled) {
                this.centre = centre;
                this.radius = radius;
                this.color = color;
                this.filled = filled;
        }

        public void draw(GraphicsContext g) {
                double x = centre.x - radius;
                double y = centre.y - radius;
                double size = radius * 2;

                g.setStroke(color);
                g.setFill(color);

                if (filled) {
                        g.fillOval(x, y, size, size);
                } else {
                        g.strokeOval(x, y, size, size);
                }
        }

        // getters / setters
        public boolean isFilled() { return filled; }
        public void setFilled(boolean filled) { this.filled = filled; }
        public void setRadius(double radius) { this.radius = radius; }
        public void setColor(Color color) { this.color = color; }
        public double getRadius() {return radius;}
        public Color getColor() {return color;}
        public Point getCentre() { return centre; }
}
