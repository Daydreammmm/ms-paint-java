package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TextShape implements Shape{

    private Point position;
    private String text;
    private Color color;


    public TextShape(Point position, String text, Color color){
        this.position = position;
        this.text = text;
        this.color = color;
    }

    public void draw(GraphicsContext g){
        g.setFill(color);
        g.fillText(text, position.x, position.y);
    }
}
