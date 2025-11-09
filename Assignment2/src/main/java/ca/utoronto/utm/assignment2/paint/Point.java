package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point {
        double x, y;// Available to our package
        double thickness;

        public Point(double x, double y, double thickness) {
                this.x = x;
                this.y = y;
                this.thickness = (thickness > 0) ? thickness : 1.0;
        }
        public double getX() {
                return x;
        }

        public double getY() {
                return y;
        }

        public double getThickness() {
                return thickness;
        }
}
