package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A text shape that can be drawn on a GraphicsContext.
 */
public class TextShape implements Shape{

    private Point position;
    private String text;
    private Color color;
    private double size;

    /**
     * Creates a textshape with the given properties.
     * @param position
     * @param text
     * @param color
     * @param size
     */
    public TextShape(Point position, String text, Color color, double size){
        this.position = position;
        this.text = text;
        this.color = color;
        this.size = size;
    }

    /**
     * Draws the text on the canvas.
     * @param g
     */
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        double fontSize = size * 5;
        g.setFont(new Font(fontSize));
        g.fillText(text, position.x, position.y);
    }

    /**
     * Returns the color of the squiggle
     * @return color of squiggle
     */
    public Color getColor(){
        return this.color;
    }

}