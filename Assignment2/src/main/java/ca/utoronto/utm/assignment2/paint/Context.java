package ca.utoronto.utm.assignment2.paint;

public final class Context {

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
            case "Eraser":
                return new EraserStrat();
            default:
                return new CircleStrat();
        }
    }
}
