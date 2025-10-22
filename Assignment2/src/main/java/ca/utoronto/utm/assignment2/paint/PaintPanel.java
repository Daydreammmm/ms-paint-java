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
    private String mode="Circle";
    private PaintModel model;

    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    public Rectangle rectangle; // Temporary rectangle during creation

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
        this.mode=mode;
        System.out.println(this.mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        switch(this.mode){
            case "Circle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.circle=new Circle(centre, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.circle!=null) {
                        // Use distance formula Sqrt((x2-x1)^2+(y2-y1)^2)
                        double radius = Math.sqrt(Math.pow((mouseEvent.getX() - this.circle.getCentre().x), 2) +
                                Math.pow((mouseEvent.getY() - this.circle.getCentre().y), 2));
                        this.circle.setRadius(radius);
                        this.update(null, null);
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.circle!=null){
                        // Problematic notion of radius and centre!!
//                        double radius = Math.sqrt(Math.pow((mouseEvent.getX() - this.circle.getCentre().x), 2) +
//                                Math.pow((mouseEvent.getY() - this.circle.getCentre().y), 2));
//                                this.circle.setRadius(radius);
                        this.model.addCircle(this.circle);
                        System.out.println("Added Circle");
                        this.circle=null;
                        this.update(null, null);
                    }
                }
                break;

            case "Rectangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Rectangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle = new Rectangle(startPoint, 0, 0);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.rectangle != null){
                        // Calculate proper rectangle coordinates (handle negative dimensions)
                        double startX = this.rectangle.getTopLeft().x;
                        double startY = this.rectangle.getTopLeft().y;
                        double endX = mouseEvent.getX();
                        double endY = mouseEvent.getY();

                        // Ensure positive width and height
                        double x = Math.min(startX, endX);
                        double y = Math.min(startY, endY);
                        double width = Math.abs(endX - startX);
                        double height = Math.abs(endY - startY);

                        this.rectangle.setTopLeft(new Point(x, y));
                        this.rectangle.setWidth(width);
                        this.rectangle.setHeight(height);

                        this.update(null, null);
                    }
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)){
                    if(this.rectangle!=null){
                        this.model.addRectangle(this.rectangle);
                        System.out.println("Added Rectangle");
                        this.rectangle = null;
                        this.update(null, null);

                    }
                }
                break;

            case "Square": break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    this.model.addPoint(new Point(Double.NaN, Double.NaN));
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    this.model.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                }
                break;
            case "Polyline": break;
            default: break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        // Draw Lines
        ArrayList<Point> points = this.model.getPoints();
        g2d.setStroke(Color.RED); // Use stroke for lines
        for(int i=0;i<points.size()-1; i++){
            Point p1=points.get(i);
            Point p2=points.get(i+1);
            g2d.strokeLine(p1.x,p1.y,p2.x,p2.y);
        }

        // Draw Circles
        ArrayList<Circle> circles = this.model.getCircles();
        g2d.setFill(Color.GREEN);
        for(Circle c: this.model.getCircles()){
            double x = c.getCentre().x;
            double y = c.getCentre().y;
            double radius = c.getRadius();
            g2d.fillOval(x-radius, y-radius, radius*2, radius*2);
        }
        if (this.circle != null) {
            double x = this.circle.getCentre().x;
            double y = this.circle.getCentre().y;
            double r = this.circle.getRadius();
            g2d.setStroke(Color.DARKGREEN);
            g2d.strokeOval(x - r, y - r, r * 2, r * 2);
        }

        // Draw Rectangles
        ArrayList<Rectangle> rectangles = this.model.getRectangles();
        g2d.setStroke(Color.BLUE);
        g2d.setFill(Color.BLUE);// Use stroke for rectangle outlines
        for(Rectangle r : rectangles){
            double x = r.getTopLeft().x;
            double y = r.getTopLeft().y;
            double width = r.getWidth();
            double height = r.getHeight();
            g2d.strokeRect(x, y, width, height);
            g2d.fillRect(x, y, width, height);
        }

        if (this.rectangle!=null){
            g2d.setStroke(Color.BLUEVIOLET);
            g2d.strokeRect(this.rectangle.getTopLeft().x, this.rectangle.getTopLeft().y,
                    this.rectangle.getWidth(), this.rectangle.getHeight());

        }
    }
}