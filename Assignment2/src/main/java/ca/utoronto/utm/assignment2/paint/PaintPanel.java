package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private DrawingStrat drawingStrat;
    private PaintModel model;

//    public Circle circle; // This is VERY UGLY, should somehow fix this!!
//    public Rectangle rectangle; // Temporary rectangle during creation
//
//    private Point startPoint;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }

    public void setMode(String mode){
        this.drawingStrat = Context.getDrawingStrat(mode, this.model);
        System.out.println(mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if(drawingStrat==null){return;}
        if (mouseEventType==MouseEvent.MOUSE_PRESSED) {
            drawingStrat.mousePressed(mouseEvent, model);
        } else if (mouseEventType==MouseEvent.MOUSE_RELEASED) {
            drawingStrat.mouseReleased(mouseEvent, model);
        }
        else if (mouseEventType==MouseEvent.MOUSE_MOVED) {
            drawingStrat.mouseMoved(mouseEvent, model);
        }
        else if (mouseEventType==MouseEvent.MOUSE_CLICKED) {
            drawingStrat.mouseClicked(mouseEvent, model);
        }
        else if (mouseEventType==MouseEvent.MOUSE_DRAGGED) {
            drawingStrat.mouseDragged(mouseEvent, model);
        }

        this.update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        // Draw Lines
        ArrayList<Point> points = this.model.getPoints();
        g2d.setStroke(Color.RED);
        for(int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw Circles
        g2d.setFill(Color.GREEN);
        for(Circle c : this.model.getCircles()) {
            double x = c.getCentre().x;
            double y = c.getCentre().y;
            double radius = c.getRadius();
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

        // Draw Rectangles
        g2d.setStroke(Color.BLUE);
        g2d.setFill(Color.BLUE);
        for(Rectangle r : this.model.getRectangles()) {
            double x = r.getTopLeft().x;
            double y = r.getTopLeft().y;
            double width = r.getWidth();
            double height = r.getHeight();
            g2d.strokeRect(x, y, width, height);
            g2d.fillRect(x, y, width, height);
        }

        ArrayList<Square> squares = this.model.getSquares();
        g2d.setStroke(Color.PURPLE);
        g2d.setFill(Color.PURPLE);
        for (Square s : squares) {
            double x = s.getTopLeft().x;
            double y = s.getTopLeft().y;
            double a = s.getSide();
            g2d.strokeRect(x, y, a, a);
            g2d.fillRect(x, y, a, a);
        }

        // Draw Triangles
        ArrayList<Triangle> triangles = this.model.getTriangles();
        for (Triangle t : triangles) {
            t.draw(g2d);
        }



        drawingStrat.drawMidConstruction(g2d);
    }
}