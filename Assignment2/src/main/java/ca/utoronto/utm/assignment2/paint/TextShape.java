package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextShape implements Shape{

    private Point position;
    private String text;
    private Color color;
    private double size;


    public TextShape(Point position, String text, Color color, double size){
        this.position = position;
        this.text = text;
        this.color = color;
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        double fontSize = size * 5;
        g.setFont(new Font(fontSize));
        g.fillText(text, position.x, position.y);
    }

    public Color getColor(){
        return this.color;
    }

}