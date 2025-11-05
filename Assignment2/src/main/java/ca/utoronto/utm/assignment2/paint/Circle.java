package ca.utoronto.utm.assignment2.paint;


public class Circle {
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

}
