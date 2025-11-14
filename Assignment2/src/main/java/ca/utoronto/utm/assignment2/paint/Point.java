package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A point with x, y coordinates and a thickness value.
 */
public class Point {
        double x, y;
        double thickness;

        /**
         * Create a point with coordinates and thickness.
         *
         * @param x
         * @param y
         * @param thickness line thickness (uses 1.0 if non-positive)
         */
        public Point(double x, double y, double thickness) {
                this.x = x;
                this.y = y;
                this.thickness = (thickness > 0) ? thickness : 1.0;
        }

        /**
         * Get the x-coordinate of this point.
         *
         * @return x-coordinate
         */
        public double getX() {
                return x;
        }

        /**
         * Get the y-coordinate of this point.
         *
         * @return y-coordinate
         */
        public double getY() {
                return y;
        }

        /**
         * Get the thickness value of this point.
         *
         * @return thickness
         */
        public double getThickness() {
                return thickness;
        }
}
