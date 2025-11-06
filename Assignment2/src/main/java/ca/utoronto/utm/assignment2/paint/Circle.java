package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Shape {
        private Point centre;
        private double radius;
        private final Color color;

        public Circle(Point centre, int radius, Color color){
                this.centre = centre;
                this.radius = radius;
                this.color = color;
        }

        public Point getCentre() {
                return centre;
        }

        public void setCentre(Point centre) {
                this.centre = centre;
        }

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }

        public Color getColor(){
            return color;
        }

        @Override
        public void draw(GraphicsContext g2d){
            g2d.setFill(color);
            double x = this.getCentre().x;
            double y = this.getCentre().y;
            double radius = this.getRadius();
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }
}
