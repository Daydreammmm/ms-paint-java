package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A circle shape that can be drawn on a GraphicsContext.
 */
public class Circle implements Shape {
        private Point centre;
        private double radius;
        private Color color;
        private boolean filled;
        private double strokeWidth;


        /**
         * Create a circle with a centre, radius, color, fill flag and stroke width.
         *
         * @param centre
         * @param radius
         * @param color
         * @param filled
         * @param strokeWidth
         */
        public Circle(Point centre, double radius, Color color, boolean filled, double strokeWidth) {
                this.centre = centre;
                this.radius = radius;
                this.color = color;
                this.filled = filled;
                this.strokeWidth = strokeWidth;
        }

        /**
         * Draw this circle on the given GraphicsContext.
         *
         * @param g
         */
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

        /**
         * Set the radius for this circle.
         *
         * @param radius
         */
        public void setRadius(double radius) {
                this.radius = radius;
        }

        /**
         * Set the color for this circle.
         *
         * @param color
         */
        public void setColor(Color color) {
                this.color = color;
        }

        /**
         * Get the radius of this circle.
         *
         * @return the radius
         */
        public double getRadius() {
                return radius;
        }

        /**
         * Get the color of this circle.
         *
         * @return the color
         */
        public Color getColor() {
                return color;
        }

        /**
         * Get the centre point of this circle.
         *
         * @return the centre point
         */
        public Point getCentre() {
                return centre;
        }

        /**
         * Get the stroke width for this circle.
         *
         * @return the stroke width
         */
        public double getStrokeWidth() {
                return strokeWidth;
        }
}
