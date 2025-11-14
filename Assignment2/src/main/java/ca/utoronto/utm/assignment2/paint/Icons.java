package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;

/**
 * Class to create icons for all the drawing styles.
 */
public class Icons {

    /**
     * Creates an array of shape icons for all the drawing modes.
     * @return array of shape icons
     */
    public static Node[] createIcons(){

        Node[] icons = new Node[7];

        // circle icon
        Circle circleIcon = new Circle(15);
        circleIcon.setFill(Color.TRANSPARENT);
        circleIcon.setStroke(Color.BLACK);
        circleIcon.setStrokeWidth(2);
        icons[0] = circleIcon;

        // rectangle icon
        Rectangle rectIcon = new Rectangle(25, 15);
        rectIcon.setFill(Color.TRANSPARENT);
        rectIcon.setStroke(Color.BLACK);
        rectIcon.setStrokeWidth(2);
        icons[1] = rectIcon;

        //square icon
        Rectangle squareIcon = new Rectangle(20, 20);
        squareIcon.setFill(Color.TRANSPARENT);
        squareIcon.setStroke(Color.BLACK);
        squareIcon.setStrokeWidth(2);
        icons[2] = squareIcon;

        // squiggle icon
        Line squiggle1 = new Line(5, 10, 15, 5);
        Line squiggle2 = new Line(15, 5, 25, 15);
        squiggle1.setStroke(Color.BLACK);
        squiggle2.setStroke(Color.BLACK);
        squiggle1.setStrokeWidth(2);
        squiggle2.setStrokeWidth(2);
        javafx.scene.Group squiggleGroup = new javafx.scene.Group(squiggle1, squiggle2);
        icons[3] = squiggleGroup;

        // Polyline icon
        Polygon polylineIcon = new Polygon();
        polylineIcon.getPoints().addAll(
                5.0, 15.0,
                15.0, 5.0,
                25.0, 15.0,
                15.0, 25.0
        );
        polylineIcon.setFill(Color.TRANSPARENT);
        polylineIcon.setStroke(Color.BLACK);
        polylineIcon.setStrokeWidth(2);
        icons[4] = polylineIcon;

        // Oval icon
        Ellipse ovalIcon = new Ellipse(10, 5);
        ovalIcon.setFill(Color.TRANSPARENT);
        ovalIcon.setStroke(Color.BLACK);
        ovalIcon.setStrokeWidth(2);
        icons[5] = ovalIcon;

        // triangle icon
        Polygon triangleIcon = new Polygon(10, 6, 20, 20, 0, 20);
        triangleIcon.setFill(Color.TRANSPARENT);
        triangleIcon.setStroke(Color.BLACK);
        triangleIcon.setStrokeWidth(2);
        icons[6] = triangleIcon;


        return icons;
    }


}
