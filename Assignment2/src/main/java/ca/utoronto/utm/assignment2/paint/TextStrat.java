package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import java.util.Optional;

/**
 * A drawing strategy implementation for text. The class handles all the mouse events and preview for the user to
 * create text on the canvas.
 */
public class TextStrat implements DrawingStrat{

    /**
     * Places text at the mousePressed location by taking user input.
     * @param e
     * @param model
     */
    @Override
    public void mousePressed(MouseEvent e, PaintModel model){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter text:");
        dialog.setContentText("Text:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().trim().isEmpty()) {
            String text = result.get();
            Point p = new Point(e.getX(), e.getY(), 1);
            TextShape shape = new TextShape(p, text, model.getCurrentColor(), model.getCurrentStrokeWidth());
            model.addShape(shape);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e, PaintModel model){}

    @Override
    public void mouseMoved(MouseEvent e, PaintModel model){}

    @Override
    public void mouseClicked(MouseEvent e, PaintModel model){}

    @Override
    public void mouseDragged(MouseEvent e, PaintModel model){}

    @Override
    public void drawMidConstruction(GraphicsContext g2d){}
}
