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


/**
 * The canvas for drawing the shapes that handles mouse events and updates the display.
 */
public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private DrawingStrat drawingStrat;
    private PaintModel model;


    /**
     * Creates a new paint panel that observes the current model.
     * @param model
     */
    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);

        this.setFocusTraversable(true);
        this.setOnMouseClicked(e -> this.requestFocus());
    }

    /**
     * Sets the drawing mode to the current drawing strategy.
     * @param mode
     */
    public void setMode(String mode){
        this.drawingStrat = Context.getDrawingStrat(mode, this.model);
        System.out.println(mode);
    }

    /**
     * Handles mouse events by assigning them to a particular strategy.
     * @param mouseEvent
     */
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

    /**
     * Updates the canvas when the model changes.
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.setFill(model.getBgColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        for(int i=0; i<this.model.getShapes().size(); i++){
            Shape shape = this.model.getShapes().get(i);
            if(shape != null){
                shape.draw(g2d);
            }
        }
        if(drawingStrat!=null) {
            drawingStrat.drawMidConstruction(g2d);
        }
    }
}