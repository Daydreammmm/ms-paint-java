package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.Node;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private Button selectedButton; // keep track of which button is selected

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };
        Node[] icons = createIcons();

        int row = 0;
        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = new Button();
            button.setGraphic(icons[i]);
            button.setMinWidth(100);
            button.setMinHeight(40);

            Tooltip tooltip = new Tooltip(buttonLabels[i]);
            Tooltip.install(button, tooltip);

            button.setUserData(buttonLabels[i]);

            this.add(button, 0, row);
            row++;
            button.setOnAction(this);
        }
    }

    private Node[] createIcons() {
        Node[] icons = new Node[5];

        Circle circleIcon = new Circle(15);
        circleIcon.setFill(Color.TRANSPARENT);
        circleIcon.setStroke(Color.BLACK);
        circleIcon.setStrokeWidth(2);
        icons[0] = circleIcon;

        Rectangle rectIcon = new Rectangle(25, 15);
        rectIcon.setFill(Color.TRANSPARENT);
        rectIcon.setStroke(Color.BLACK);
        rectIcon.setStrokeWidth(2);
        icons[1] = rectIcon;

        Rectangle squareIcon = new Rectangle(20, 20);
        squareIcon.setFill(Color.TRANSPARENT);
        squareIcon.setStroke(Color.BLACK);
        squareIcon.setStrokeWidth(2);
        icons[2] = squareIcon;

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

        return icons;
    }

    @Override
    public void handle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        String command = (String) ((Button) event.getSource()).getUserData();
        view.setMode(command);
        System.out.println(command);

        highlightButton(clickedButton);
    }

    private void highlightButton(Button button) {
        if (selectedButton != null) {
            // Reset the previous button to its normal look
            selectedButton.setStyle("");
        }

        selectedButton = button;
        selectedButton.setStyle(
                "-fx-background-color: #87CEFA;"
        );
    }
}