package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private Button selectedButton; // keep track of which button is selected

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
            button.setOnAction(this);

            this.add(button, 0, row);
            row++;
        }
    }

    @Override
    public void handle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String mode = (String) clickedButton.getUserData();

        view.setMode(mode);
        System.out.println(mode);

        highlightButton(clickedButton);
    }

    private void highlightButton(Button button) {
        if (selectedButton != null) {
            selectedButton.setStyle("");
        }
        selectedButton = button;
        selectedButton.setStyle("-fx-background-color: #87CEFA;");
    }
}
