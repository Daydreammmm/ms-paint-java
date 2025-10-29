package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CircleStrat implements DrawingStrat{

    private Circle circle;

    @Override
    public void mousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Circle");
        Point centre = new Point(e.getX(), e.getY());
        circle = new Circle(centre, 0);
    }

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model) {
        if(circle != null) {
            double radius = Math.sqrt(Math.pow((e.getX() - this.circle.getCentre().x), 2) +
                    Math.pow((e.getY() - this.circle.getCentre().y), 2));
            circle.setRadius(radius);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, PaintModel model) {
        if(circle != null) {
            model.addCircle(circle);
            System.out.println("Added circle");
            circle = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model) {}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model) {}

    @Override
    public void drawMidConstruction(GraphicsContext g2d) {
        if (circle != null) {
            double x = circle.getCentre().x;
            double y = circle.getCentre().y;
            double r = circle.getRadius();
            g2d.setStroke(Color.GREEN);
            g2d.strokeOval(x - r, y - r, r * 2, r * 2);
        }
    }

}
