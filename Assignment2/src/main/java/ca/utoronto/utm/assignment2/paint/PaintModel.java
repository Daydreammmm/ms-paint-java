package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;


public class PaintModel extends Observable {
        private ArrayList<Shape> shapes = new ArrayList<Shape>();
        private Color currentColor = Color.BLACK;



    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
        this.setChanged();
        this.notifyObservers(shape);
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

}

