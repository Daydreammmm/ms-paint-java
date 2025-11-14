package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Shape {
        private Point centre;
        private double radius;
        private Color color;
        private boolean filled;
        private double strokeWidth;

        public Circle(Point centre, double radius, Color color, boolean filled, double strokeWidth) {
                this.centre = centre;
                this.radius = radius;
                this.color = color;
                this.filled = filled;
                this.strokeWidth = strokeWidth;
        }

        public void draw(GraphicsContext g) {
                double x = centre.x - radius;
                double y = centre.y - radius;
                double size = radius * 2;

                g.setStroke(color);
                g.setFill(color);
                g.setLineWidth(strokeWidth);

                if (filled) {
                        g.fillOval(x, y, size, size);
                } else {
                        g.strokeOval(x, y, size, size);
                }
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getRadius() {
            return radius;
        }

        public Color getColor() {
            return color;
        }

        public Point getCentre() {
            return centre;
        }

        public double getStrokeWidth() {
            return strokeWidth;
        }
}
