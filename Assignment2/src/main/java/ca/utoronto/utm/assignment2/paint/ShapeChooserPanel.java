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

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private Button selectedButton;// keep track of which button is selected

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Oval", "Triangle" };
        Node[] icons = Icons.createIcons();

        int row = 0;
        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = new Button();
            button.setMinWidth(100);
            button.setMinHeight(40);
            button.setGraphic(icons[i]);

            Tooltip tooltip = new Tooltip(buttonLabels[i]);
            Tooltip.install(button, tooltip);

            button.setUserData(buttonLabels[i]);

            this.add(button, 0, row);
            row++;
            button.setOnAction(this);
        }
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
            selectedButton.setStyle("");
        }

        selectedButton = button;
        selectedButton.setStyle(
                "-fx-background-color: #87CEFA;"
        );
    }
}