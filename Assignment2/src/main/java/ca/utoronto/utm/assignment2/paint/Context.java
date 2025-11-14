package ca.utoronto.utm.assignment2.paint;

/**
 * Context class to return the correct drawing strategy based on what the user selects.
 */
public final class Context {

    /**
     * Returns the right strategy for the current drawing mode.
     * @param mode
     * @param model
     * @return a valid drawing strategy
     */
    public static DrawingStrat getDrawingStrat(String mode, PaintModel model) {
        switch (mode) {
            case "Circle":
                return new CircleStrat();
            case "Rectangle":
                return new RectangleStrat();
            case "Squiggle":
                return new SquiggleStrat();
            case "Triangle":
                return new TriangleStrat(model);
            case "Square":
                return new SquareStrat();
            case "Oval":
                return new OvalStrat();
            case "Polyline":
                return new PolylineStrat();
            case "Text":
                return new TextStrat();
            case "Eraser":
                return new EraserStrat();
            default:
                return new CircleStrat();
        }
    }
}
