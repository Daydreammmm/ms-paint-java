package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;
import java.util.Deque;
import java.util.ArrayDeque;


/**
 * Model class for the paint application.
 *
 * Stores all shapes and current drawing settings, and notifies observers
 * whenever the model changes.
 */
public class PaintModel extends Observable {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Color currentColor = Color.BLACK;
    private double currentStrokeWidth = 1.0;

    private boolean darkMode = false;
    private Color bgColor = Color.web("#F5F5F5");
    private boolean fillMode = false;

    /**
     * Return whether fill mode is currently enabled.
     *
     * @return true if shapes should be filled, false otherwise
     */
    public boolean isFillMode() {
        return fillMode;
    }

    /**
     * Toggle the fill mode setting and notify observers.
     */
    public void toggleFillMode() {
        fillMode = !fillMode;
        this.setChanged();
        this.notifyObservers();

    }

    /**
     * Get the current drawing color.
     *
     * @return current Color used for new shapes
     */
    public Color getCurrentColor() {
        return currentColor;
    }

    /**
     * Set the current drawing color.
     *
     * @param color new Color used for new shapes
     */
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    /**
     * Get the current opacity of the drawing color.
     *
     * @return opacity of currentColor in the range [0, 1]
     */
    public double getCurrentOpacity() {
        return currentColor.getOpacity();
    }

    /**
     * Toggle dark mode and update the background color.
     *
     * Also updates any Squiggle whose color matches the old background color
     * so they remain visible against the new background.
     */
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

    /**
     * Return whether dark mode is currently enabled.
     *
     * @return true if dark mode is on, false otherwise
     */
    public boolean getDarkMode(){
        return darkMode;
    }

    /**
     * Get the current background color.
     *
     * @return background color
     */
    public Color getBgColor(){
        return this.bgColor;
    }

    /**
     * Add a shape to the model and record the change for undo/redo.
     *
     * @param shape shape to be added
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
        undoStack.push(new Change(Change.Type.ADD, shape, shapes.size() - 1));
        redoStack.clear();
        this.setChanged();
        this.notifyObservers(shape);
    }

    /**
     * Get the current stroke width used for new shapes.
     *
     * @return stroke width
     */
    public double getCurrentStrokeWidth() {
        return currentStrokeWidth;
    }

    /**
     * Set the stroke width for new shapes and notify observers.
     *
     * @param w new stroke width (must be > 0)
     */
    public void setCurrentStrokeWidth(double w) {
        if (w <= 0) return;
        this.currentStrokeWidth = w;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Delete the last shape in the list, if any, and record the change
     * for undo/redo.
     */
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

    /**
     * Get the list of all shapes in the model.
     *
     * @return list of shapes
     */
    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    /**
     * Represents a single change (add or delete) for undo/redo.
     */
    private static class Change {
        enum Type { ADD, DELETE }
        final Type type;
        final Shape shape;
        final int index;

        /**
         * Create a Change describing an add or delete operation.
         *
         * @param type  type of change (ADD or DELETE)
         * @param shape shape involved in the change
         * @param index index of the shape in the list at the time of change
         */
        Change(Type type, Shape shape, int index) {
            this.type = type; this.shape = shape; this.index = index;
        }
    }
    private final Deque<Change> undoStack = new ArrayDeque<>();
    private final Deque<Change> redoStack = new ArrayDeque<>();

    /**
     * Undo the most recent change, if any, and notify observers.
     *
     * Restores the previous state by reversing the last add or delete.
     */
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

    /**
     * Redo the most recently undone change, if any, and notify observers.
     *
     * Reapplies the last change that was undone.
     */
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

