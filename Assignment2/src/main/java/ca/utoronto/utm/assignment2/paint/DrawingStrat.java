package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

public interface DrawingStrat {
    void mousePressed(MouseEvent e, PaintModel model);
    void mouseReleased(MouseEvent e, PaintModel model);
    void mouseMoved(MouseEvent e, PaintModel model);
    void mouseClicked(MouseEvent e, PaintModel model);
    void mouseDragged(MouseEvent e, PaintModel model);
    void drawMidConstruction(GraphicsContext g2d);
}

