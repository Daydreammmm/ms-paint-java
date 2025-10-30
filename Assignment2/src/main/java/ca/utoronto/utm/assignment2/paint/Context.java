package ca.utoronto.utm.assignment2.paint;

public class Context {
    public static DrawingStrat getDrawingStrat(String mode) {
        switch(mode) {
            case "Circle":
                return new CircleStrat();
            case "Rectangle":
                return new RectangleStrat();
            case "Squiggle":
                return new SquiggleStrat();
            case "Square":
                return new SquareStrat();
            default:
                return new CircleStrat();
        }
    }
}
