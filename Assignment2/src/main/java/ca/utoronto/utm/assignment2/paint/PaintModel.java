package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;
import java.util.Deque;
import java.util.ArrayDeque;



public class PaintModel extends Observable {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Color currentColor = Color.BLACK;
    private double currentStrokeWidth = 1.0;

    private boolean darkMode = false;
    private Color bgColor = Color.web("#F5F5F5");
    private boolean fillMode = false;
    public boolean isFillMode() {
        return fillMode;
    }
    public void toggleFillMode() {
        fillMode = !fillMode;
        this.setChanged();
        this.notifyObservers();

    }
    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public double getCurrentOpacity() {
        return currentColor.getOpacity();
    }

    public void setDarkMode() {

        Color previousColor = this.bgColor;
        darkMode = !darkMode;
        if (!darkMode) {
            this.bgColor = Color.web("#F5F5F5");
        }
        else {
            this.bgColor = Color.web("#1E1E1E");
        }

        for(int i=0; i<shapes.size(); i++){
            if(shapes.get(i) instanceof Squiggle shape){
                if(shape.getColor().equals(previousColor)){
                    shape.setColor(this.bgColor);
                }
            }
        }
        this.setChanged();
        this.notifyObservers();
    }

    public boolean getDarkMode(){
        return darkMode;
    }
    public Color getBgColor(){
        return this.bgColor;
    }
    public void addShape(Shape shape) {
        this.shapes.add(shape);
        undoStack.push(new Change(Change.Type.ADD, shape, shapes.size() - 1));
        redoStack.clear();
        this.setChanged();
        this.notifyObservers(shape);
    }

    public double getCurrentStrokeWidth() {
        return currentStrokeWidth;
    }

    public void setCurrentStrokeWidth(double w) {
        if (w <= 0) return;
        this.currentStrokeWidth = w;
        this.setChanged();
        this.notifyObservers();
    }

    public void deleteLast() {
        if (this.shapes.isEmpty()) return;
        int idx = this.shapes.size() - 1;
        Shape removed = this.shapes.remove(idx);
        undoStack.push(new Change(Change.Type.DELETE, removed, idx));
        redoStack.clear();
        this.setChanged();
        this.notifyObservers();
        System.out.println("Deleted shape");
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    private static class Change {
        enum Type { ADD, DELETE }
        final Type type;
        final Shape shape;
        final int index;
        Change(Type type, Shape shape, int index) {
            this.type = type; this.shape = shape; this.index = index;
        }
    }
    private final Deque<Change> undoStack = new ArrayDeque<>();
    private final Deque<Change> redoStack = new ArrayDeque<>();

    public void undo() {
        if (undoStack.isEmpty()) return;
        Change c = undoStack.pop();

        if (c.type == Change.Type.ADD) {
            if (c.index >= 0 && c.index < shapes.size()) {
                shapes.remove(c.index);
            } else if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
            }
            redoStack.push(new Change(Change.Type.ADD, c.shape, c.index));
        } else {
            int insertIdx = Math.min(Math.max(c.index, 0), shapes.size());
            shapes.add(insertIdx, c.shape);
            redoStack.push(new Change(Change.Type.DELETE, c.shape, c.index));
        }

        this.setChanged();
        this.notifyObservers();
    }

    public void redo() {
        if (redoStack.isEmpty()) return;
        Change c = redoStack.pop();

        if (c.type == Change.Type.ADD) {
            int insertIdx = Math.min(Math.max(c.index, 0), shapes.size());
            shapes.add(insertIdx, c.shape);
            undoStack.push(new Change(Change.Type.ADD, c.shape, c.index));
        } else {
            if (c.index >= 0 && c.index < shapes.size()) {
                shapes.remove(c.index);
            } else if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
            }
            undoStack.push(new Change(Change.Type.DELETE, c.shape, c.index));
        }

        this.setChanged();
        this.notifyObservers();
    }







}

