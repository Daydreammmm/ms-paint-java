package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        private ArrayList<Oval> ovals = new ArrayList<Oval>();
        private ArrayList<Square> squares = new ArrayList<Square>();
        private ArrayList<Triangle> triangles = new ArrayList<Triangle>();


        public void addPoint(Point p){
                this.points.add(p);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Point> getPoints(){
                return points;
        }

        public void addCircle(Circle c){
                this.circles.add(c);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Circle> getCircles(){
                return circles;
        }

        public void addRectangle(Rectangle r) { // Add this method
            this.rectangles.add(r);
            this.setChanged();
            this.notifyObservers();
        }

        public ArrayList<Rectangle> getRectangles() { // Add this method
            return rectangles;
        }

        public void addOval(Oval o) {
            this.ovals.add(o);
            this.setChanged();
            this.notifyObservers();
    }
    public ArrayList<Oval> getOvals() { return ovals;}



        public void addSquare(Square s) {
            this.squares.add(s);
            this.setChanged();
            this.notifyObservers();
        }

        public ArrayList<Square> getSquares() { return squares; }

        public void addTriangle(Triangle t) {
            this.triangles.add(t);
            this.setChanged();
            this.notifyObservers();
        }
        public ArrayList<Triangle> getTriangles() {
            return triangles;
        }

}

